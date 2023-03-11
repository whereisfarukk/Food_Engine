package com.example.food_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.food_engine.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void register(View view) {
        startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
    }

    public void signInComplete(View view) {
        String phoneNumber = binding.phoneNumber.getText().toString();
        String password = binding.password.getText().toString();
        if (phoneNumber.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please type your phone number and password", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("type", 2);
            intent.putExtra("phoneNumber", phoneNumber);
            startActivity(intent);
        }
    }
}