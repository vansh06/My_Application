package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;


public class Num_verify extends AppCompatActivity {
    Button verifybuttonclick;
    ProgressBar verifyprogressbar = findViewById(R.id.progress_otp_verifybar);
    EditText inputnumber1,inputnumber2,inputnumber3,inputnumber4,inputnumber5,inputnumber6;
    String getbackendotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_verify);

        verifybuttonclick = findViewById(R.id.verify_button);

         inputnumber1 = findViewById(R.id.input1);
         inputnumber2 = findViewById(R.id.input2);
         inputnumber3 = findViewById(R.id.input3);
         inputnumber4 = findViewById(R.id.input4);
         inputnumber5 = findViewById(R.id.input5);
         inputnumber6 = findViewById(R.id.input6);

        TextView textView = findViewById(R.id.text3);
        textView.setText(String.format(
                "+91-%s", getIntent().getStringExtra("mobile")
        ));

        getbackendotp=getIntent().getStringExtra("backendotp");

        verifybuttonclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!inputnumber1.getText().toString().trim().isEmpty() && !inputnumber2.getText().toString().trim().isEmpty()  &&  !inputnumber3.getText().toString().trim().isEmpty()  &&  !inputnumber4.getText().toString().trim().isEmpty()  &&  !inputnumber5.getText().toString().trim().isEmpty()  &&  !inputnumber6.getText().toString().trim().isEmpty()){
                    String enteredcodeotp = inputnumber1.getText().toString()+
                            inputnumber2.getText().toString() +
                            inputnumber3.getText().toString() +
                            inputnumber4.getText().toString() +
                            inputnumber5.getText().toString() +
                            inputnumber6.getText().toString();

                    if(getbackendotp!=null){
                        verifyprogressbar.setVisibility(View.VISIBLE);
                        verifybuttonclick.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                getbackendotp,enteredcodeotp
                        );

                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        verifyprogressbar.setVisibility(View.GONE);
                                        verifybuttonclick.setVisibility(View.VISIBLE);

                                        if(task.isSuccessful()){
                                            Intent i =new Intent(getApplicationContext(),dashboard.class);
                                            i.setFlags(i.FLAG_ACTIVITY_NEW_TASK | i.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(i);
                                        }
                                        else{
                                            Toast.makeText(Num_verify.this,"Enter the correct otp",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }else{
                        Toast.makeText(Num_verify.this,"Please check your connection",Toast.LENGTH_SHORT).show();
                    }



                     //Toast.makeText(Num_verify.this, "otp verify", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Num_verify.this, "please enter all numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });

        numberotpmove();
    }

    private void numberotpmove()  {


        inputnumber1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    inputnumber2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputnumber2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    inputnumber3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputnumber3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    inputnumber4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputnumber4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    inputnumber5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputnumber5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    inputnumber6  .requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}