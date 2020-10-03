package com.example.payment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;

public class MainActivity extends AppCompatActivity {
private Button btnconfirm;

  private   Button btnupdate ;
  Member member;

  RadioButton reservePaymentOption,master,visa;
EditText txtname, txtdate , txtamount, txthname , txtexdate , txtcnum;
Button btnsave, btnshow, btndelete;
RadioGroup reservePayment;
    DatabaseReference dbRef;
    reservationPayment Rpayment;
int i=0;
String strpaymentmethod;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtname = findViewById(R.id.ETname);
        txtdate = findViewById(R.id.ETdate);
        txtamount = findViewById(R.id.ETamount);
        txthname = findViewById(R.id.ETholdername);
        txtexdate = findViewById(R.id.ETedate);
        txtcnum = findViewById(R.id.ETcnumber);

        reservePayment = findViewById(R.id.rpayment);


         master = findViewById(R.id.ButradioMaster);
          visa = findViewById(R.id.ButradioVisa);
        btndelete = findViewById(R.id.Butdelete);
        btnsave = findViewById(R.id.Butsave);
        btnshow = findViewById(R.id.Butshow);
        btnupdate = findViewById(R.id.Butupdate);


        Rpayment = new reservationPayment();



        reservePayment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                reservePaymentOption = reservePayment.findViewById(i);

                switch(i){
                    case R.id.ButradioMaster:

                    case R.id.ButradioVisa:
                        strpaymentmethod = reservePaymentOption.getText().toString();
                        break;

                    default:
                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("reservationPayment");
                dbRef.removeValue();
                Toast.makeText(getApplicationContext(), "Succesfully deleted your reservation payment", Toast.LENGTH_SHORT).show();
                clearControls();
            }
        });


        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference();
                dbRef.child("reservationPayment").child("name").setValue(txtname.getText().toString().trim());
                dbRef.child("reservationPayment/date").setValue(txtdate.getText().toString().trim());
                dbRef.child("reservationPayment/amount").setValue(txtamount.getText().toString().trim());
                dbRef.child("reservationPayment/holdername").setValue(txthname.getText().toString().trim());
                dbRef.child("reservationPayment/expdate").setValue(txtexdate.getText().toString().trim());
                dbRef.child("reservationPayment/cardnum").setValue(txtcnum.getText().toString().trim());


                Toast.makeText(getApplicationContext(),  "Succesfully updated your updated details", Toast.LENGTH_SHORT). show();
                clearControls();
            }
        });

        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("reservationPayment");
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()){

                            txtname.setText(snapshot.child("name").getValue().toString());
                            txtdate.setText(snapshot.child("date").getValue().toString());
                            txtamount.setText(snapshot.child("amount").getValue().toString());
                            txthname.setText(snapshot.child("holdername").getValue().toString());
                            txtexdate.setText(snapshot.child("expdate").getValue().toString());
                            txtcnum.setText(snapshot.child("cardnum").getValue().toString());
                        }
                        else
                            Toast.makeText(getApplicationContext(),  "Cannot find Payment details", Toast.LENGTH_SHORT). show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("reservationPayment");
                String P1= master.getText().toString();
                String P2=visa.getText().toString();


                if(master.isChecked()){
                    Rpayment.setPaymentmethod(P1);
                    dbRef.child(String.valueOf(i+1)).setValue(member);
                }else{

                    Rpayment.setPaymentmethod(P2);
                    dbRef.child(String.valueOf(i+1)).setValue(member);
                }

                try{
                    if(TextUtils.isEmpty(reservePayment.toString()))
                        Toast.makeText(getApplicationContext(),  "Empty payment method", Toast.LENGTH_SHORT). show();
                 else  if(TextUtils.isEmpty(txtname.getText().toString()))
                        Toast.makeText(getApplicationContext(),  "Empty Name", Toast.LENGTH_SHORT). show();
                    else if(TextUtils.isEmpty(txtdate.getText().toString()))
                        Toast.makeText(getApplicationContext(),  "Empty Date", Toast.LENGTH_SHORT). show();
                    else if(TextUtils.isEmpty(txtamount.getText().toString()))
                        Toast.makeText(getApplicationContext(),  "Empty Amount", Toast.LENGTH_SHORT). show();
                    else if(TextUtils.isEmpty(txthname.getText().toString()))
                        Toast.makeText(getApplicationContext(),  "Empty card holder name", Toast.LENGTH_SHORT). show();
                    else if(TextUtils.isEmpty(txtexdate.getText().toString()))
                        Toast.makeText(getApplicationContext(),   "Empty expiry Date", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtcnum.getText().toString()))
                        Toast.makeText(getApplicationContext(),   "Empty card number", Toast.LENGTH_SHORT).show();

                    else{
                        
                        Rpayment.setName((txtname.getText().toString().trim()));
                        Rpayment.setDate(txtdate.getText().toString().trim());
                        Rpayment.setAmount(Double.parseDouble(txtamount.getText().toString().trim()));
                        Rpayment.setHoldername(txthname.getText().toString().trim());
                        Rpayment.setExpdate(txtexdate.getText().toString().trim());
                        Rpayment.setCardnum(Integer.parseInt(txtcnum.getText().toString().trim()));
                        dbRef.setValue(Rpayment);
                        Toast.makeText(getApplicationContext(),  "Successfully inserted your payment details", Toast.LENGTH_SHORT). show();
                        clearControls();
                    }

                }catch (NumberFormatException nfe ){
                    Toast.makeText(getApplicationContext(),  "Invalid  card number", Toast.LENGTH_SHORT). show();
                }

            }
        });

        btnconfirm = (Button) findViewById(R.id.Butconfirm);
        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(TextUtils.isEmpty(reservePayment.toString()))
                        Toast.makeText(getApplicationContext(),  "Empty payment method", Toast.LENGTH_SHORT). show();
                    else  if(TextUtils.isEmpty(txtname.getText().toString()))
                        Toast.makeText(getApplicationContext(),  "Empty Name", Toast.LENGTH_SHORT). show();
                    else if(TextUtils.isEmpty(txtdate.getText().toString()))
                        Toast.makeText(getApplicationContext(),  "Empty Date", Toast.LENGTH_SHORT). show();
                    else if(TextUtils.isEmpty(txtamount.getText().toString()))
                        Toast.makeText(getApplicationContext(),  "Empty Amount", Toast.LENGTH_SHORT). show();
                    else if(TextUtils.isEmpty(txthname.getText().toString()))
                        Toast.makeText(getApplicationContext(),  "Empty card holder name", Toast.LENGTH_SHORT). show();
                    else if(TextUtils.isEmpty(txtexdate.getText().toString()))
                        Toast.makeText(getApplicationContext(),   "Empty expiry Date", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtcnum.getText().toString()))
                        Toast.makeText(getApplicationContext(),   "Empty card number", Toast.LENGTH_SHORT).show();

                    else{

                        Rpayment.setName((txtname.getText().toString().trim()));
                        Rpayment.setDate(txtdate.getText().toString().trim());
                        Rpayment.setAmount(Double.parseDouble(txtamount.getText().toString().trim()));
                        Rpayment.setHoldername(txthname.getText().toString().trim());
                        Rpayment.setExpdate(txtexdate.getText().toString().trim());
                        Rpayment.setCardnum(Integer.parseInt(txtcnum.getText().toString().trim()));
                        dbRef.setValue(Rpayment);
                        Toast.makeText(getApplicationContext(),  "Successfully inserted your payment details", Toast.LENGTH_SHORT). show();
                        clearControls();
                        Toast.makeText(getApplicationContext(),  "your payment is successfull", Toast.LENGTH_SHORT). show();

                        openConfirmpage();
                    }

                }catch (NumberFormatException nfe ){
                    Toast.makeText(getApplicationContext(),  "Invalid  card number", Toast.LENGTH_SHORT). show();
                }



            }
        });
    }
        public void openConfirmpage() {

            Intent intent = new Intent(this, Confirmpage.class );
            startActivity(intent);

        }

    private void clearControls() {


        txtname.setText("");
        txtdate.setText("");
        txtamount.setText("");
        txthname.setText("");
        txtexdate.setText("");
        txtcnum.setText("");
    }
}