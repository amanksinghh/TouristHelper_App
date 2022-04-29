package com.example.touristhelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    TextView mAlready;
    EditText mEmail,mPassword;
    Button mSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        mEmail = findViewById(R.id.rEmail);
        mPassword = findViewById(R.id.rPass);
        mAlready = findViewById(R.id.already);
        mSignup = findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();

        mAlready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
        if (mAuth.getCurrentUser()!= null)
        {
            Intent intent= new Intent(Register.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

         public void createUser() {
            String email = mEmail.getText().toString();
            String pass = mPassword.getText().toString();
            if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                if (!pass.isEmpty()){
                    mAuth.createUserWithEmailAndPassword(email,pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(Register.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Register.this,Login.class));
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Register.this, "Registration Error", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    mPassword.setError("Empty Fields are not Allowed");
                }
            } else if(email.isEmpty()){
                mEmail.setError("Empty fields Are not Allowed");
            }else{
                mEmail.setError("please Enter correct email");
            }

        }

}