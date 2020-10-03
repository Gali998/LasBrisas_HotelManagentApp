package com.example.hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Retrieve_room_details extends AppCompatActivity {
    ListView myRoomListView;
    List<Reservation>ListReserve;

    DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_room_details);

        myRoomListView = findViewById(R.id.myRoomListView);
        ListReserve = new ArrayList<>();

        dbRef = FirebaseDatabase.getInstance().getReference("Reservation");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ListReserve.clear();

                for(DataSnapshot roomDatasnap:dataSnapshot.getChildren()){

                    Reservation reservation = roomDatasnap.getValue(Reservation.class);
                    ListReserve.add(reservation);

                }

                //ListAdapter adapter = new ListAdapter(Retrieve_room_details.this,ListReserve);
                //myRoomListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
