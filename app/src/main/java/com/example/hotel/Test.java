package com.example.hotel;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.apache.http.conn.ConnectTimeoutException;

public class Test extends AppCompatActivity {

    EditText roomname1, norooms, noadults, nokids, name1, email1, ConNo;
    EditText date;
    EditText date2;
    DatePickerDialog datePickerDialog;
    DatePickerDialog datePickerDialog1;
    Button button, btnShow1, btnUpdate1, btnDelete1,btn3;

    DatabaseReference dbref,dbRef;
    AlertDialog.Builder builder;
    Reservation reservation;

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



        reservation = new Reservation();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
                Toast.makeText(getApplicationContext(), "Click", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void uploadFile() {
        try {
            dbRef = FirebaseDatabase.getInstance().getReference().child("HallReservation");


            reservation = new Reservation();

            reservation.setRname(roomname1.getText().toString());

            reservation.setName(name1.getText().toString());
            reservation.setContactNo(ConNo.getText().toString());


            dbRef.push().setValue(reservation);
            Toast.makeText(getApplicationContext(), "sUCCESS", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Error : " + e, Toast.LENGTH_SHORT).show();
        }
    }


}
