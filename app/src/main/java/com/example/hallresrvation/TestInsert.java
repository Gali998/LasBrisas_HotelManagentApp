package com.example.hallresrvation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TestInsert extends AppCompatActivity {


    EditText hallName, NoOfG, GuestN, conNo, email1, add;
    EditText date1;
    EditText time1;
    Button button;
    AlertDialog.Builder builder;
    Spinner spinner;
    Hall hall;
    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_insert);


        hallName = findViewById(R.id.hname);
        button = findViewById(R.id.button);
        spinner = findViewById(R.id.spinner);
        date1 = findViewById(R.id.date);
        NoOfG = findViewById(R.id.guest);
        time1 = findViewById(R.id.time);
        GuestN = findViewById(R.id.guestN);
        conNo = findViewById(R.id.conNo);
        email1 = findViewById(R.id.email);
        add = findViewById(R.id.addr);

        hall = new Hall();

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


            hall = new Hall();

            hall.setHallName(hallName.getText().toString());

            hall.setTime(time1.getText().toString());
            hall.setContactNo(conNo.getText().toString());


            dbRef.push().setValue(hall);
            Toast.makeText(getApplicationContext(), "sUCCESS", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Error : " + e, Toast.LENGTH_SHORT).show();
        }
    }


}