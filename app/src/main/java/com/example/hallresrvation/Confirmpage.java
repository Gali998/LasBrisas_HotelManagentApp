package com.example.hallresrvation;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Confirmpage extends AppCompatActivity {

Button bth1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmpage);

bth1= findViewById(R.id.btnBhome);

bth1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Confirmpage.this,Dashboard2.class);
        // Intent intent = new Intent(Dashboard2.this,.class);
        startActivity(intent);
    }
});
    }

}