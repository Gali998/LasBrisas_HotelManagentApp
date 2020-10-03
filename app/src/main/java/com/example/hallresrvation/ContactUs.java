package com.example.hallresrvation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ContactUs extends AppCompatActivity {

    EditText txtName, txtEmail, txtConNo, txtMessage;
    Button btnSave, btnShow, btnUpdate, btnDelete;
    DatabaseReference dbRef;
    Contact ctd;
    DataSnapshot dataSnapshot;

    private void clearControls() {
        txtName.setText("");
        txtEmail.setText("");
        txtConNo.setText("");
        txtMessage.setText("");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        txtName = findViewById(R.id.name1);
        txtEmail = findViewById(R.id.email);
        txtConNo = findViewById(R.id.conNo);
        txtMessage = findViewById(R.id.message);

        btnSave = findViewById(R.id.btnSave);
        btnShow = findViewById(R.id.btnShow);
        btnUpdate = findViewById(R.id.btnUpdated);
       btnDelete = findViewById(R.id.btnDelete);

        ctd = new Contact();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Contact");
                try {
                    if (TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(ContactUs.this.getApplicationContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtEmail.getText().toString()))
                        Toast.makeText(ContactUs.this.getApplicationContext(), "Please enter an email", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(txtConNo.getText().toString()))
                        Toast.makeText(ContactUs.this.getApplicationContext(), "Please enter a contact No", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtMessage.getText().toString()))
                        Toast.makeText(ContactUs.this.getApplicationContext(), "Please enter a Message", Toast.LENGTH_SHORT).show();
                    else {

                        ctd.setName(txtName.getText().toString().trim());
                        ctd.setEmail(txtEmail.getText().toString().trim());
                        ctd.setConNo(Integer.parseInt(txtConNo.getText().toString().trim()));
                        ctd.setMessage(txtMessage.getText().toString().trim());
                        dbRef.setValue(ctd);
                       // dbRef.push().setValue(ctd);
                        dbRef.child("ctd1").setValue(ctd);
                        Toast.makeText(ContactUs.this.getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        ContactUs.this.clearControls();
                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(ContactUs.this.getApplicationContext(), "Invalid contact Number", Toast.LENGTH_SHORT).show();
                }

            }
        });btnShow.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Contact/ctd1");
                                           readRef.addListenerForSingleValueEvent(new ValueEventListener() {

                                               @Override
                                               public void onDataChange(DataSnapshot dataSnapshot) {
                                                   if (dataSnapshot.hasChildren()) {
                                                       txtName.setText(dataSnapshot.child("name").getValue().toString());
                                                       txtEmail.setText(dataSnapshot.child("email").getValue().toString());
                                                       txtConNo.setText(dataSnapshot.child("conNo").getValue().toString());
                                                       txtMessage.setText(dataSnapshot.child("message").getValue().toString());

                                                   } else
                                                       Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                                               }


                                               @Override
                                               public void onCancelled(@NonNull DatabaseError databaseError) {

                                               }

                                           });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Contact");
                //upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                // @Override
                //public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // if (dataSnapshot.hasChild("Ctd2")) {
                //try {
                ctd.setName(txtName.getText().toString().trim());
                ctd.setEmail(txtEmail.getText().toString().trim());
                ctd.setConNo(Integer.parseInt(txtConNo.getText().toString().trim()));
                ctd.setMessage(txtMessage.getText().toString().trim());


                //dbRef = FirebaseDatabase.getInstance().getReference().child("Contact").child("Ctd2");
                dbRef = FirebaseDatabase.getInstance().getReference();
                dbRef.child("Contact/ctd1/name").setValue(txtName.getText().toString().trim());
                dbRef.child("Contact/ctd1/email").setValue(txtEmail.getText().toString().trim());
                dbRef.child("Contact/ctd1/conNo").setValue(Integer.parseInt(txtConNo.getText().toString().trim()));
                dbRef.child("Contact/ctd1/message").setValue(txtMessage.getText().toString().trim());


                // dbRef.setValue(ctd);
                clearControls();

                //Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();

                //} catch (NumberFormatException e) {
                //Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                // }
                // } else
                //Toast.makeText(getApplicationContext(), "No source to update", Toast.LENGTH_SHORT).show();
                //}

                // @Override
                // public void onCancelled(@NonNull DatabaseError databaseError) {

            }
                });

                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Contact");
                        //delRef.addListenerForSingleValueEvent(new ValueEventListener() {

                        dbRef = FirebaseDatabase.getInstance().getReference().child("Contact").child("ctd1");
                        dbRef.removeValue();
                        //dbRef.child("cus1").setValue(cus);

                        Toast.makeText(getApplicationContext(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                    }

                });


        }
    public void onBackPressed()
    {

        // Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(ContactUs.this);

        // Set the message show for the Alert time
        builder.setMessage("Do you want to exit ?");

        // Set Alert Title
        builder.setTitle("Alert !");

        // Set Cancelable false
        // for when the user clicks on the outside
        // the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name
        // OnClickListener method is use of
        // DialogInterface interface.

        builder
                .setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                                // When the user click yes button
                                // then app will close
                                finish();
                            }
                        });

        // Set the Negative button with No name
        // OnClickListener method is use
        // of DialogInterface interface.
        builder
                .setNegativeButton(
                        "No",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {

                                // If user click no
                                // then dialog box is canceled.
                                dialog.cancel();
                            }
                        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();
    }
});
    }
}
