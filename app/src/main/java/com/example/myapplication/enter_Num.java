package com.example.myapplication;

import static com.google.firebase.auth.PhoneAuthProvider.verifyPhoneNumber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class enter_Num extends AppCompatActivity {

    EditText enternumber;
    Button get_button_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_num);

        enternumber=findViewById(R.id.number);
        get_button_otp=findViewById(R.id.button_otp);

        ProgressBar progressBar = findViewById(R.id.progress_otp_bar);

        get_button_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!enternumber.getText().toString().trim().isEmpty()){
                    if((enternumber.getText().toString().trim()).length() == 10){


                        progressBar.setVisibility(View.VISIBLE);
                        get_button_otp.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + enternumber.getText().toString(),
                                60, TimeUnit.SECONDS, enter_Num.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        progressBar.setVisibility(View.GONE);
                                        get_button_otp.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressBar.setVisibility(View.GONE);
                                        get_button_otp.setVisibility(View.VISIBLE);
                                        Toast.makeText(enter_Num.this, "Please check your connection", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        progressBar.setVisibility(View.GONE);
                                        get_button_otp.setVisibility(View.VISIBLE);
                                        Intent i = new Intent(getApplicationContext(),Num_verify.class);
                                        i.putExtra("mobile",enternumber.getText().toString());
                                        i.putExtra("backendotp",backendotp);
                                        startActivity(i);
                                    }
                                }
                        );
                    }else {
                        Toast.makeText(enter_Num.this, "Please enter correct number ", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(enter_Num.this, "Enter Your Phone Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}