package com.example.hallresrvation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class roomdetails extends AppCompatActivity {

    Button button;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomdetails);


        button  = (Button) findViewById(R.id.button9);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(roomdetails.this, FamilyRoom.class);
                startActivity(intent);

            }
        });
                button  = (Button) findViewById(R.id.button10);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(roomdetails.this, RoomReservation.class);
                startActivity(intent);

            }
        } );
        button  = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(roomdetails.this, StandardRoom.class);
                startActivity(intent);

            }
        } );
        button  = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(roomdetails.this, EconomyRoom.class);
                startActivity(intent);

            }
        } );
        button  = (Button) findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(roomdetails.this, TripleRoom.class);
                startActivity(intent);

            }
        });
        button  = (Button) findViewById(R.id.button8);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(roomdetails.this, RoomReservation.class);
                startActivity(intent);

            }
        });
        button  = (Button) findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(roomdetails.this, RoomReservation.class);
                startActivity(intent);

            }
        });

        button  = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(roomdetails.this, RoomReservation.class);
                startActivity(intent);

            }
        });
    }
}




