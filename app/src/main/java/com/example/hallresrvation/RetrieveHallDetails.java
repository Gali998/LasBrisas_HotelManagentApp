package com.example.hallresrvation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RetrieveHallDetails extends AppCompatActivity {

    ListView myHallListView;
    List<Hall> hallList;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_hall_details);

        myHallListView = findViewById(R.id.myHallListView);
        hallList = new ArrayList<>();

        dbRef = FirebaseDatabase.getInstance().getReference("Hall");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hallList.clear();

                for(DataSnapshot hallDatasnap:dataSnapshot.getChildren()){

                    Hall hall = hallDatasnap.getValue(Hall.class);
                    hallList.add(hall);

                }

                ListAdapter adapter = new ListAdapter(RetrieveHallDetails.this,hallList);
                myHallListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}