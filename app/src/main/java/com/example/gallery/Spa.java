package com.example.gallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Spa extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    Button button5,button4,button3,butDelete,butShow,butUpdate,butBooking;
    private static final String TAG = "Spa";

     private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    EditText txtName,txtPhone,txtDate,txtTime;
    Spinner txtPackage;
    DatabaseReference dbRef;
    SpaPackage spack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spa);


        txtName = findViewById(R.id.eInputID1);
        txtPhone = findViewById(R.id.eInputID2);
        txtPackage = findViewById(R.id.Spinner1);
        txtDate = findViewById(R.id.editTextDate);
        txtTime = findViewById(R.id.eInputID3);

        butBooking = findViewById(R.id.button2);
        butShow = findViewById(R.id.button13);
        butUpdate = findViewById(R.id.button14);
        butDelete = findViewById(R.id.button15);

        spack = new SpaPackage();

      
        butUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference();
                dbRef.child("SpaPackage/spack2/name").setValue(txtName.getText().toString().trim());
                dbRef.child("SpaPackage/spack2/phoneNo").setValue(Integer.parseInt(txtPhone.getText().toString().trim()));
                dbRef.child("SpaPackage/spack2/packName").setValue(txtPackage.getSelectedItem().toString().trim());
                dbRef.child("SpaPackage/spack2/date").setValue(txtDate.getText().toString().trim());
                dbRef.child("SpaPackage/spack2/time").setValue(txtTime.getText().toString().trim());


                clearControls();
            }
        });
        butShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("SpaPackage/spack2");

                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()){
                            txtName.setText(dataSnapshot.child("name").getValue().toString());
                            txtPhone.setText(dataSnapshot.child("phoneNo").getValue().toString());
                           // txtPackage.setOnItemSelectedListener(dataSnapshot.child("packName").getValue().toString());
                            txtDate.setText(dataSnapshot.child("date").getValue().toString());
                            txtTime.setText(dataSnapshot.child("time").getValue().toString());


                        }
                        else
                            Toast.makeText(getApplicationContext(),"Cannot find spack2",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
            }
        });


        butBooking.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){

                                              dbRef = FirebaseDatabase.getInstance().getReference().child("SpaPackage");
                                              try {
                                                  if (TextUtils.isEmpty(txtName.getText().toString()))
                                                      Toast.makeText(getApplicationContext(), "Please enter a Name", Toast.LENGTH_SHORT).show();


                                                  else if (TextUtils.isEmpty(txtPhone.getText().toString()))
                                                      Toast.makeText(getApplicationContext(), "Please enter a phone number", Toast.LENGTH_SHORT).show();
                                                  else if (TextUtils.isEmpty(txtDate.getText().toString()))
                                                      Toast.makeText(getApplicationContext(), "Please enter a date", Toast.LENGTH_SHORT).show();
                                                  else if (TextUtils.isEmpty(txtTime.getText().toString()))
                                                      Toast.makeText(getApplicationContext(), "Please enter a time", Toast.LENGTH_SHORT).show();

                                                  else {
                                                      spack.setName(txtName.getText().toString().trim());
                                                      spack.setPhoneNo(Integer.parseInt(txtPhone.getText().toString().trim()));
                                                      spack.setPackName(txtPackage.getSelectedItem().toString().trim());
                                                      spack.setDate(txtDate.getText().toString().trim());
                                                      spack.setTime(txtTime.getText().toString().trim());
                                                      dbRef.child("spack2").setValue(spack);
                                                      Toast.makeText(getApplicationContext(), "Successfully inserted", Toast.LENGTH_SHORT).show();
                                                      clearControls();
                                                  }
                                              } catch (NumberFormatException nfe) {
                                                  Toast.makeText(getApplicationContext(), "Invalid phone no", Toast.LENGTH_SHORT).show();
                                              }
                                          }

                                      });
        mDisplayDate = (TextView) findViewById(R.id.editTextDate);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Spa.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };






        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPackage1();

            }
        });
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openPackage2();

            }
        });
        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openPackage3();
            }
        });
        Spinner spinner = findViewById(R.id.Spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Packages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void clearControls() {

        txtName.setText("");
        txtPhone.setText("");
        txtDate.setText("");
        txtTime.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
    private void openPackage1() {
        Intent intent1 = new Intent(Spa.this,Package1.class);
        startActivity(intent1);
    }

    private void openPackage2() {
        Intent intent2 = new Intent(Spa.this,package3.class);
        startActivity(intent2);
    }

    private void openPackage3() {
        Intent intent3 = new Intent(Spa.this,Package2.class);
        startActivity(intent3);

    }

    }
