package com.example.hallresrvation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ItemListHall extends AppCompatActivity implements ImgAdapter.onItemClickListener {

    private RecyclerView mRecyclerView;
    private ImgAdapter mAdapter;
    private DatabaseReference dbRef;
    private List<Hall> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list_hall);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUploads = new ArrayList<>();

        mAdapter = new ImgAdapter(ItemListHall.this,mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(ItemListHall.this);

        dbRef = FirebaseDatabase.getInstance().getReference().child("HallReservation");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mUploads.clear();
                //To get the single upload item
                for (DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    Hall hall = postSnapshot.getValue(Hall.class);
                    hall.setKey(postSnapshot.getKey());
                    //Adding hall details to the created child class
                    mUploads.add(hall);
                }

                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(ItemListHall.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onItemClick(int position) {

        Toast.makeText(this, "Normal Click at position "+position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditClick(int position) {

        Toast.makeText(this, "Edit Click at position "+position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {

        Hall selectedItem = mUploads.get(position);
        String selectedKey = selectedItem.getKey();

        dbRef.child(selectedKey).removeValue();
        Toast.makeText(this, "Data Deleted", Toast.LENGTH_SHORT).show();

        //Toast.makeText(this, "Delete Click at position "+position, Toast.LENGTH_SHORT).show();

    }
}