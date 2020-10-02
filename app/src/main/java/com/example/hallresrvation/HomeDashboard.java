package com.example.hallresrvation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class HomeDashboard extends AppCompatActivity {

    ImageView mainImage;
    Button welcome;
    Animation forimg,frombottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dashboard);

        mainImage = (ImageView) findViewById(R.id.mainImage);
        welcome = (Button)findViewById(R.id.welcome);

        forimg = AnimationUtils.loadAnimation(this,R.anim.forimg);
        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);

        mainImage.startAnimation(forimg);
        welcome.startAnimation(frombottom);

        welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeDashboard.this,Dashboard2.class);
                startActivity(i);
            }
        });
    }
}