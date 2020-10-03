package com.example.hotel;

import androidx.annotation.NonNull;
import
        androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.apache.http.conn.ConnectTimeoutException;

import java.util.Calendar;

public class RoomReservation extends AppCompatActivity {


    EditText roomname1, norooms, noadults, nokids, name1, email1, ConNo;
    EditText date;
    EditText date2;
    DatePickerDialog datePickerDialog;
    DatePickerDialog datePickerDialog1;
    Button button, btnShow1, btnUpdate1, btnDelete1, btn3;

    DatabaseReference dbRef;
    AlertDialog.Builder builder;
    Reservation reservation;

    private void clearControls() {
        date.setText("");
        date2.setText("");
        roomname1.setText("");
        norooms.setText("");
        noadults.setText("");
        nokids.setText("");
        name1.setText("");
        email1.setText("");
        ConNo.setText("");


    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_reservation);

        reservation = new Reservation();
        date = findViewById(R.id.date);
        date2 = findViewById(R.id.date2);
        roomname1 = findViewById(R.id.roomname);
        norooms = findViewById(R.id.rooms);
        noadults = findViewById(R.id.adults1);
        nokids = findViewById(R.id.kids);
        name1 = findViewById(R.id.myName);
        email1 = findViewById(R.id.myEmail);
        ConNo = findViewById(R.id.mycontactNo);


        btnShow1 = findViewById(R.id.Roomshow);
        btnUpdate1 = findViewById(R.id.Roomupdate);
        btnDelete1 = findViewById(R.id.Roomdelete);

        reservation = new Reservation();
        //tevent = findViewById(R.id.spinner);
        // dbRef = FirebaseDatabase.getInstance().getReference().child("Reservation");
        // btnShow1 = (Button) findViewById(R.id.Roomshow);
        // btnShow1.setOnClickListener(new View.OnClickListener() {

        //btnBook = findViewById(R.id.btnBook);)
        button = (Button) findViewById(R.id.btnBook);
        builder = new AlertDialog.Builder(this);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("Reservation");
                try {
                    if (TextUtils.isEmpty(date.getText().toString()))
                        Toast.makeText(RoomReservation.this.getApplicationContext(), "Please enter a check in date", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(date2.getText().toString()))
                        Toast.makeText(RoomReservation.this.getApplicationContext(), "Please enter a check out date", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(roomname1.getText().toString()))
                        Toast.makeText(RoomReservation.this.getApplicationContext(), "Please enter a  room name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(norooms.getText().toString()))
                        Toast.makeText(RoomReservation.this.getApplicationContext(), "Please enter a  No of rooms", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(noadults.getText().toString()))
                        Toast.makeText(RoomReservation.this.getApplicationContext(), "Please enter a  No of adults", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(nokids.getText().toString()))
                        Toast.makeText(RoomReservation.this.getApplicationContext(), "Please enter a  No of kids", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(name1.getText().toString()))
                        Toast.makeText(RoomReservation.this.getApplicationContext(), "Please enter a  guset name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(email1.getText().toString()))
                        Toast.makeText(RoomReservation.this.getApplicationContext(), "Please enter an email address", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(ConNo.getText().toString()))
                        Toast.makeText(RoomReservation.this.getApplicationContext(), "Please enter a  Contact No", Toast.LENGTH_SHORT).show();

                    else {

                        reservation.setCheckin(date.getText().toString().trim());
                        reservation.setCheckout(date2.getText().toString().trim());
                        reservation.setRname(roomname1.getText().toString().trim());
                        reservation.setRooms(norooms.getText().toString().trim());
                        reservation.setAdults(noadults.getText().toString().trim());
                        reservation.setKids(nokids.getText().toString().trim());
                        reservation.setName(name1.getText().toString().trim());
                        reservation.setEmail(email1.getText().toString().trim());
                        reservation.setContactNo(ConNo.getText().toString().trim());

                        dbRef.setValue(reservation);
                        // dbRef.push().setValue(ctd);
                        dbRef.child("reservation1").setValue(reservation);
                        Toast.makeText(RoomReservation.this.getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(RoomReservation.this.getApplicationContext(), "Invalid contact Number", Toast.LENGTH_SHORT).show();
                }


                btnShow1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Reservation/reservation1");
                        readRef.addListenerForSingleValueEvent(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChildren()) {
                                    date.setText(dataSnapshot.child("checkin").getValue().toString());
                                    date2.setText(dataSnapshot.child("checkout").getValue().toString());
                                    roomname1.setText(dataSnapshot.child("rname").getValue().toString());
                                    norooms.setText(dataSnapshot.child("rooms").getValue().toString());
                                    noadults.setText(dataSnapshot.child("adults").getValue().toString());
                                    nokids.setText(dataSnapshot.child("kids").getValue().toString());
                                    name1.setText(dataSnapshot.child("name").getValue().toString());
                                    email1.setText(dataSnapshot.child("email").getValue().toString());
                                    ConNo.setText(dataSnapshot.child("contactNo").getValue().toString());

                                } else
                                    Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                            }


                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }

                        });


                        btnUpdate1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                reservation.setCheckin(date.getText().toString().trim());
                                reservation.setCheckout(date2.getText().toString().trim());
                                reservation.setRname(roomname1.getText().toString().trim());
                                reservation.setRooms(norooms.getText().toString().trim());
                                reservation.setAdults(noadults.getText().toString().trim());
                                reservation.setKids(nokids.getText().toString().trim());
                                reservation.setName(name1.getText().toString().trim());
                                reservation.setEmail(email1.getText().toString().trim());
                                reservation.setContactNo(ConNo.getText().toString().trim());


                                //dbRef = FirebaseDatabase.getInstance().getReference().child("Contact").child("Ctd2");
                                dbRef = FirebaseDatabase.getInstance().getReference();
                                dbRef.child("Reservation/reservation1/checkin").setValue(date.getText().toString().trim());
                                dbRef.child("Reservation/reservation1/checkout").setValue(date2.getText().toString().trim());
                                dbRef.child("Reservation/reservation1/rname").setValue(roomname1.getText().toString().trim());
                                dbRef.child("Reservation/reservation1/rooms").setValue(norooms.getText().toString().trim());
                                dbRef.child("Reservation/reservation1/adults").setValue(noadults.getText().toString().trim());
                                dbRef.child("Reservation/reservation1/kids").setValue(nokids.getText().toString().trim());
                                dbRef.child("Reservation/reservation1/name").setValue(name1.getText().toString().trim());
                                dbRef.child("Reservation/reservation1/email").setValue(email1.getText().toString().trim());
                                dbRef.child("Reservation/reservation1/contactNo").setValue(ConNo.getText().toString().trim());


                                // dbRef.setValue(ctd);
                                clearControls();

                                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();

                                // } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                                // }
                                // } else
                                Toast.makeText(getApplicationContext(), "No source to update", Toast.LENGTH_SHORT).show();
                                //}

                                // @Override
                                // public void onCancelled(@NonNull DatabaseError databaseError) {

                                // }
                                // });
                            }
                        });
                        btnDelete1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                //DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Contact");
                                //delRef.addListenerForSingleValueEvent(new ValueEventListener() {

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Reservation").child("reservation1");
                                dbRef.removeValue();
                                //dbRef.child("reservation1").setValue(reservation);

                                Toast.makeText(getApplicationContext(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                            }

                        });


                        // @Override
                        // public void onClick(View v) {
                        // insertReservationData();
                        // }builder.setMessage("Do you want to Book this Room ?")
                        //  @Override
                        // public void onClick(View view) {

                        builder.setMessage("Do you want to book this room now ?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        finish();
                                        Toast.makeText(RoomReservation.this.getApplicationContext(), "Booking success",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  Action for 'NO' Button
                                        dialog.cancel();
                                        Toast.makeText(RoomReservation.this.getApplicationContext(), "booking canceled",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                        //Creating dialog box
                        AlertDialog alert = builder.create();
                        //Setting the title manually
                        alert.setTitle("Booking successfull");
                        alert.show();


                        //Uncomment the below code to Set the message and title from the strings.xml file

                        date = (EditText) findViewById(R.id.date);
                        // perform click event on edit text
                        date.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
// calender class's instance and get current date , month and year from calender
                                final Calendar c = Calendar.getInstance();
                                int mYear = c.get(Calendar.YEAR); // current year
                                int mMonth = c.get(Calendar.MONTH); // current month
                                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                                // date picker dialog
                                datePickerDialog = new DatePickerDialog(RoomReservation.this, new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {
                                        // set day of month , month and year value in the edit text
                                        date.setText(dayOfMonth + "/"
                                                + (monthOfYear + 1) + "/" + year);

                                    }
                                }, mYear, mMonth, mDay);
                                datePickerDialog.show();
                            }
                        });

                        date2 = (EditText) findViewById(R.id.date2);
                        // perform click event on edit text
                        date2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
// calender class's instance and get current date , month and year from calender
                                final Calendar c = Calendar.getInstance();
                                int mYear = c.get(Calendar.YEAR); // current year
                                int mMonth = c.get(Calendar.MONTH); // current month
                                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                                // date picker dialog
                                datePickerDialog1 = new DatePickerDialog(RoomReservation.this, new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {
                                        // set day of month , month and year value in the edit text
                                        date.setText(dayOfMonth + "/"
                                                + (monthOfYear + 1) + "/" + year);

                                    }
                                }, mYear, mMonth, mDay);
                                datePickerDialog1.show();
                            }
                        });
                    }
                });
            }
        });
    }
}





