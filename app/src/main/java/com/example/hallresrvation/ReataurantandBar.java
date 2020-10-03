package com.example.hallresrvation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

class ReataurantandBar extends AppCompatActivity {
     Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantand_bar);

        button1 = findViewById(R.id.barbtn1);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReataurantandBar.this, Bar.class );
                startActivity(intent);
            }


        });
    }


}