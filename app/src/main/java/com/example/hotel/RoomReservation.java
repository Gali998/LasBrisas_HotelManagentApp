package com.example.hotel;

import
        androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class RoomReservation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText date;
    EditText date2;
    DatePickerDialog datePickerDialog;
    DatePickerDialog datePickerDialog1;
    Button button;
    AlertDialog.Builder builder;

    EditText txtName, txtEmail, txtConNo;
    Button btnBook, btnShow, btnUpdate, btnDelete;
    DatabaseReference dbRef;
    //Rerservation rtd;
    DataSnapshot dataSnapshot;

    private void clearControls() {
        txtName.setText("");
        txtEmail.setText("");
        txtConNo.setText("");

    }
    private Spinner spinner;
    private Spinner spinner1;
    private Spinner spinner2;

    private static final String[] paths = {"2", "4", "5 or more"};
    private static final String[] paths1 = {"2", "4", "5 or more"};
    private static final String[] paths2 = {"1", "2", "3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_reservation);

        txtName = findViewById(R.id.myName);
        txtEmail = findViewById(R.id.myEmail);
        txtConNo = findViewById(R.id.mycontactNo);

       // rtd= new Reservation();
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Rerservation");
                try {
                    if (TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(RoomReservation.this.getApplicationContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtEmail.getText().toString()))
                        Toast.makeText(RoomReservation.this.getApplicationContext(), "Please enter an email", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(txtConNo.getText().toString()))
                        Toast.makeText(RoomReservation.this.getApplicationContext(), "Please enter a contact No", Toast.LENGTH_SHORT).show();

                    else {

                        //rtd.setName(txtName.getText().toString().trim());
                        //rtd.setEmail(txtEmail.getText().toString().trim());
                        //rtd.setConNo(Integer.parseInt(txtConNo.getText().toString().trim()));

                        //dbRef.push().setValue(rtd);
                        //dbRef.child("Rtd1").setValue(rtd);
                        Toast.makeText(RoomReservation.this.getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        RoomReservation.this.clearControls();
                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(RoomReservation.this.getApplicationContext(), "Invalid contact Number", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnBook = findViewById(R.id.btnBook);
        button = (Button) findViewById(R.id.btnBook);
        builder = new AlertDialog.Builder(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Uncomment the below code to Set the message and title from the strings.xml file


                //Setting message manually and performing action on button click
                builder.setMessage("Do you want to Book this Room ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(),"you choose yes action for alertbox",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(),"you choose no action for alertbox",
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
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RoomReservation.this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        adapter = new ArrayAdapter<String>(RoomReservation.this,
                android.R.layout.simple_spinner_item, paths1);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        adapter = new ArrayAdapter<String>(RoomReservation.this,
                android.R.layout.simple_spinner_item, paths2);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }



}




