package com.example.hallresrvation;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Feedback extends AppCompatActivity {

    Button butDelete,butShow,butUpdate,butSend;

    EditText txtName,txtEmail,txtFeedback;
    RatingBar rating;
    DatabaseReference dbRef;
    CusFeedback cus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        txtName = findViewById(R.id.edinput1);
        txtEmail = findViewById(R.id.edinput2);
        txtFeedback = findViewById(R.id.edinput3);
        rating = findViewById(R.id.ratingBar);

        butSend = findViewById(R.id.button6);
        butShow = findViewById(R.id.button9);
        butUpdate = findViewById(R.id.button7);
        butDelete = findViewById(R.id.button8);

        cus = new CusFeedback();
        butDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("CusFeedback").child("cus1");
                dbRef.removeValue();
                Toast.makeText(getApplicationContext(),"Successfully deleted", Toast.LENGTH_SHORT).show();

            }
        });


        butUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference();
                dbRef.child("CusFeedback/cus1/name").setValue(txtName.getText().toString().trim());
                dbRef.child("CusFeedback/cus1/email").setValue(txtEmail.getText().toString().trim());
                dbRef.child("CusFeedback/cus1/feedback").setValue(txtFeedback.getText().toString().trim());
                float rateValue=rating.getRating();
                dbRef.child("CusFeedback/cus1/rating").setValue(Float.parseFloat(String.valueOf(rateValue)));
                clearControls();
            }
        });


        butShow.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
    dbRef = FirebaseDatabase.getInstance().getReference().child("CusFeedback/cus1");
    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(dataSnapshot.hasChildren()){
            txtName.setText(dataSnapshot.child("name").getValue().toString());
            txtEmail.setText(dataSnapshot.child("email").getValue().toString());
            txtFeedback.setText(dataSnapshot.child("feedback").getValue().toString());
            rating.setRating(Float.parseFloat(dataSnapshot.child("rating").getValue().toString()));

        }
        else
            Toast.makeText(getApplicationContext(),"Cannot find Cus1", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});

                                       }
                                   });


        butSend.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                    dbRef = FirebaseDatabase.getInstance().getReference().child("CusFeedback");
                try {
                    if (TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a Name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtEmail.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a Email", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtFeedback.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a Feedback", Toast.LENGTH_SHORT).show();
                    else {
                        cus.setName(txtName.getText().toString().trim());
                        cus.setEmail(txtEmail.getText().toString().trim());
                        cus.setFeedback(txtFeedback.getText().toString().trim());
                        float rateValue=rating.getRating();
                        cus.setRating(Float.parseFloat(String.valueOf(rateValue)));

                        dbRef.child("cus1").setValue(cus);
                        Toast.makeText(getApplicationContext(), "Successfully inserted", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                } catch (NumberFormatException nfe) {
                    Toast.makeText(getApplicationContext(), "Invalid phone no", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
    private void clearControls() {

        txtName.setText("");
        txtEmail.setText("");
        txtFeedback.setText("");

    }
}