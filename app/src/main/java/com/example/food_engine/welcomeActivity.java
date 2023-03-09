package com.example.food_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class welcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void register(View view) {
        startActivity(new Intent(welcomeActivity.this,RegistrationActivity.class));
    }

    public void signin(View view) {
        startActivity(new Intent(welcomeActivity.this,LoginActivity.class));
    }
}