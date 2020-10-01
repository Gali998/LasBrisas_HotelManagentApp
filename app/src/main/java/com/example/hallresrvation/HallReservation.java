package com.example.hallresrvation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class HallReservation extends AppCompatActivity{


    DatabaseReference dbRef;

    EditText hallName, NoOfG, GuestN, conNo, email1, add;
    EditText date;
    DatePickerDialog datePickerDialog;
    EditText time1;
    Button button,btn2;
    AlertDialog.Builder builder;
    Spinner spinner1;

    Hall hall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_reservation);
        hall = new Hall();

        hallName = findViewById(R.id.hname);
        button = findViewById(R.id.button);
        spinner1= findViewById(R.id.spinner);
        date = findViewById(R.id.date);
        NoOfG = findViewById(R.id.guest);
        time1 = findViewById(R.id.time);
        GuestN = findViewById(R.id.guestN);
        conNo = findViewById(R.id.conNo);
        email1 = findViewById(R.id.email);
        add = findViewById(R.id.addr);


        dbRef = FirebaseDatabase.getInstance().getReference().child("HallReservation");

        // initiate the date picker and a button
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
                datePickerDialog = new DatePickerDialog(HallReservation.this,
                        new DatePickerDialog.OnDateSetListener() {

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

        //To view the hall details
        btn2 = (Button) findViewById(R.id.showHall);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(HallReservation.this,RetrieveHallDetails.class );
                //startActivity(intent);
                openActivity();
            }
        });

        //To book the hall
        button = (Button) findViewById(R.id.button);
        builder = new AlertDialog.Builder(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertHallData();
                //Uncomment the below code to Set the message and title from the strings.xml file


                //Setting message manually and performing action on button click
                builder.setMessage("Do you want to Proceed?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(), "Reservation Success !",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(), "Cancel the reservation",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Reservation");
                alert.show();
            }
        });

    }


    private void insertHallData() {
        dbRef = FirebaseDatabase.getInstance().getReference().child("HallReservation");
        String HallName = hallName.getText().toString();
        String TypeEvent =spinner1.getSelectedItem().toString();
        //String date = datePickerDialog.getDatePicker().toString();
        String NoOfGuest = NoOfG.getText().toString();
        String time = time1.getText().toString();
        String GuestName = GuestN.getText().toString();
        String contactNo = conNo.getText().toString();
        String email = email1.getText().toString();
        String address = add.getText().toString();

       // Log.d("typevent", "insertHallData: " +TypeEvent);

        Hall hall = new Hall(HallName,TypeEvent, NoOfGuest, time, GuestName, contactNo, email, address);

        dbRef.push().setValue(hall);
        Toast.makeText(HallReservation.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
    }

    private void openActivity(){
        Intent intent = new Intent(this,ItemListHall.class);
        startActivity(intent);

    }
}