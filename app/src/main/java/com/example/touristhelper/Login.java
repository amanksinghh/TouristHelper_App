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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText mEmail,mPassword;
    Button mSignin;
    TextView mNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.lEmail);
        mPassword = findViewById(R.id.lPass);
        mSignin = findViewById(R.id.signin);
        mAuth = FirebaseAuth.getInstance();
        mNew = findViewById(R.id.newUser);

        getSupportActionBar().hide();

        mNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this , Register.class));
            }
        });

        mSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        if (mAuth.getCurrentUser()!= null)
        {
            Intent intent= new Intent(Login.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

        private void loginUser(){
            String email = mEmail.getText().toString();
            String pass = mPassword.getText().toString();
            if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                if (!pass.isEmpty()){
                    mAuth.signInWithEmailAndPassword(email,pass)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(Login.this, "Login Successfully ", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Login.this,MainActivity.class));
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Login.this, "Login Failed!!", Toast.LENGTH_SHORT).show();
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