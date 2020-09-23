package com.example.hallresrvation;

import androidx.appcompat.app.AppCompatActivity;

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

public class HallReservation extends AppCompatActivity  implements
        AdapterView.OnItemSelectedListener {


    DatabaseReference dbRef;

    EditText hallName,NoOfG,GuestN,conNo,email1,add;
    EditText date;
    DatePickerDialog datePickerDialog;
    EditText time1;
    Button button;
    AlertDialog.Builder builder;
    private Spinner spinner;
    String[] tevent = { "wedding", "business"};
    //private static final String[] paths = {"wedding", "business"};
    Hall hall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_reservation);

        hallName = findViewById(R.id.hname);
        //tevent = findViewById(R.id.spinner);
        date = findViewById(R.id.date);
        NoOfG = findViewById(R.id.guest);
        time1 = findViewById(R.id.time);
        GuestN = findViewById(R.id.guestN);
        conNo = findViewById(R.id.conNo);
        email1 = findViewById(R.id.email);
        add = findViewById(R.id.addr);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Hall");
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, tevent);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);

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


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(getApplicationContext(),tevent[i] , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void insertHallData(){

        String HallName = hallName.getText().toString();
        String TypeEvent =spinner.getSelectedItem().toString();
        String date =datePickerDialog.getDatePicker().toString();
        String NoOfGuest =NoOfG.getText().toString();
        String time =time1.getText().toString();
        String GuestName=GuestN.getText().toString();
        String contactNo=conNo.getText().toString();
        String email=email1.getText().toString();
        String address=add.getText().toString();

        Hall hall = new Hall(HallName,TypeEvent,date,NoOfGuest,time,GuestName,contactNo,email,address);

        dbRef.push().setValue(hall);
        Toast.makeText(HallReservation.this,"Data Inserted Successfully",Toast.LENGTH_SHORT).show();
    }

}