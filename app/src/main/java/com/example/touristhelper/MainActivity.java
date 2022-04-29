package com.example.touristhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    String[] cities = {"Select City", "Agra", "Allahabad", "Gorakhpur", "Ghaziabad"};
    String[] inActivities = {"null", "InAgra"};

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();

        TextView logout = findViewById(R.id.logout);
        Button searchButton = findViewById(R.id.SearchButton);
        Spinner spinner = findViewById(R.id.citiesSpinner);
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cities);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(ad);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    Toast.makeText(getApplicationContext(),"Please select a city", Toast.LENGTH_LONG).show();
                }
                if (position==1) {
                    Toast.makeText(getApplicationContext(), "you selected " + cities[position], Toast.LENGTH_SHORT).show();
                    searchButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(MainActivity.this , InAgra.class));
                        }
                    });
                }
                if (position==2) {
                    Toast.makeText(getApplicationContext(), "you selected " + cities[position], Toast.LENGTH_SHORT).show();
                    searchButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, InAllahabad.class);
                            startActivity(intent);
                        }
                    });
                }
                if (position==3) {
                    Toast.makeText(getApplicationContext(), "you selected " + cities[position], Toast.LENGTH_SHORT).show();
                    searchButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, InGorakhpur.class);
                            startActivity(intent);
                        }
                    });
                }
                if (position==4) {
                    Toast.makeText(getApplicationContext(), "you selected " + cities[position], Toast.LENGTH_SHORT).show();
                    searchButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, InGhaziabad.class);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Handling Logout button and its functions
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });
    }
}