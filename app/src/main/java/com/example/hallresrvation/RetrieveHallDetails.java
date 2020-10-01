package com.example.hallresrvation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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
    String guestName;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_hall_details);

        myHallListView = findViewById(R.id.myHallListView);
        hallList = new ArrayList<>();

        dbRef = FirebaseDatabase.getInstance().getReference("HallReservation");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hallList.clear();

                for (DataSnapshot hallDatasnap : dataSnapshot.getChildren()) {

                    Hall hall = hallDatasnap.getValue(Hall.class);
                    hallList.add(hall);

                }

                ListAdapter adapter = new ListAdapter(RetrieveHallDetails.this, hallList);
                myHallListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

/*
        //set itemlong listner on list view
        myHallListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Hall hall = hallList.get(i);
                showUpdateDialog(hall.getGuestName());
                return false;
            }
        });
    }

    private void showDeleteDataDialog(final String name){
        AlertDialog .Builder builder = new AlertDialog.Builder(Comments.this);
        builder.setTitle("Delete");
        builder.setMessage("Are you Sure to Delete this Data");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Query query = databaseReference.orderByChild("name").equalTo(name);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            ds.getRef().removeValue();
                        }
                        Toast.makeText(Comments.this, "Data deleted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

    private void showUpdateDialog(String guestName){
        AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View mDialogView = inflater.inflate(R.layout.update_dialog,null);

        mDialog.setView(mDialogView);

        //create references
        final EditText etUpdateHallName = mDialogView.findViewById(R.id.etUpdateHallName);
        final Spinner updateSpinner = mDialogView.findViewById(R.id.updateSpinner);
        final EditText etUpdateNGuest = mDialogView.findViewById(R.id.etUpdateNGuest);
        final EditText etUpdateTime = mDialogView.findViewById(R.id.etUpdateTime);
        final EditText etUpdateGuestName = mDialogView.findViewById(R.id.etUpdateGuestName);
        final EditText etUpdateConNo = mDialogView.findViewById(R.id.etUpdateConNo);
        final EditText etUpdateEmail = mDialogView.findViewById(R.id.etUpdateEmail);
        final EditText etUpdateAddress = mDialogView.findViewById(R.id.etUpdateAddress);
        Button btnUpdate = mDialogView.findViewById(R.id.btnUpdate);
        Button btnDelete = mDialogView.findViewById(R.id.btnDelete);

        mDialog.setTitle("Updating "+guestName+"record");

        final AlertDialog alertDialog = mDialog.create();
        alertDialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //update in database
                String newHallName = etUpdateHallName.getText().toString();
                String newSpinner = updateSpinner.getSelectedItem().toString();
                String newGuest = etUpdateNGuest.getText().toString();
                String newTime = etUpdateTime.getText().toString();
                String newGuestName = etUpdateGuestName.getText().toString();
                String newCon = etUpdateConNo.getText().toString();
                String newEmail = etUpdateEmail.getText().toString();
                String newAddress = etUpdateAddress.getText().toString();

                updateData(newHallName,newSpinner,newGuest,newTime,newGuestName,newCon,newEmail,newAddress);

                Toast.makeText(RetrieveHallDetails.this,"Record Updated",Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference dbRef =
            }
        });

    }

    private void updateData(String hallName,String typeEvent,String noOfGuest,String time,String guestName,String contactNo,String email,String address){
        //creating database reference
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("HallReservation").child(id);
        Hall hall = new Hall(hallName,typeEvent,noOfGuest,time,guestName,contactNo,email,address);
        dbRef.setValue(hall);
    }*/
    }
}

