package com.example.hotel;

import
        androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class RoomReservation extends AppCompatActivity   {
    DatabaseReference dbRef;

    EditText  norooms,noadults,nokids,name1, email1, ConNo;
    EditText date;
    EditText date2;
    DatePickerDialog datePickerDialog;
    DatePickerDialog datePickerDialog1;
    Button button;


    AlertDialog.Builder builder;
   Reservation reservation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_reservation);

        reservation = new Reservation();
        date = findViewById(R.id.date);
        date2 = findViewById(R.id.date2);
        norooms = findViewById(R.id.rooms);
        noadults = findViewById(R.id.adults1);
        nokids = findViewById(R.id.kids);
        name1 = findViewById(R.id.myName);
        email1 = findViewById(R.id.myEmail);
        ConNo = findViewById(R.id.mycontactNo);


        //tevent = findViewById(R.id.spinner);
        dbRef = FirebaseDatabase.getInstance().getReference().child("Reservation");


        //btnBook = findViewById(R.id.btnBook);
        button = (Button) findViewById(R.id.btnBook);
        builder = new AlertDialog.Builder(this);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                insertReservationData();
                //Uncomment the below code to Set the message and title from the strings.xml file


                //Setting message manually and performing action on button click
                builder.setMessage("Do you want to Book this Room ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(), "you choose yes action for alertbox",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(), "you choose no action for alertbox",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Booking");
                alert.show();
            }
        });
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
    //Performing action onItemSelected and onNothing selected
    private void insertReservationData(){
        dbRef = FirebaseDatabase.getInstance().getReference().child("Reservation");
        //String date1 =datePickerDialog.getDatePicker().toString();
       //String date2 =datePickerDialog1.getDatePicker().toString();
        String rooms =norooms.getText().toString();
        String adults=noadults.getText().toString();
        String kids=nokids.getText().toString();
        String name=name1.getText().toString();
        String email=email1.getText().toString();
        String contactNo=ConNo.getText().toString();

        //Reservation reservation = new Reservation( date1,date2,rooms,adults,kids,name,email,contactNo);
        Reservation reservation = new Reservation( rooms,adults,kids,name,email,contactNo);
        dbRef.push().setValue(reservation);
        Toast.makeText(RoomReservation.this,"Data Inserted Successfully",Toast.LENGTH_SHORT).show();
    }



}












