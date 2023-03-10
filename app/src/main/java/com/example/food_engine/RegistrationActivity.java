package com.example.food_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.food_engine.databinding.ActivityRegistrationBinding;

public class RegistrationActivity extends AppCompatActivity {

    ActivityRegistrationBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void signin(View view) {
        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
    }

    public void regComplete(View view) {
        String userName = binding.username.getText().toString();
        String phoneNumber = binding.phoneNumber.getText().toString();
        String password = binding.password.getText().toString();

        if (userName.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(RegistrationActivity.this, "Name or phone number can't be empty", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            Toast.makeText(RegistrationActivity.this, "Password must be atleast 6 characters", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
            intent.putExtra("type", 1);
            intent.putExtra("userName", userName);
            intent.putExtra("phoneNumber", phoneNumber);
            intent.putExtra("password", password);

            startActivity(intent);
        }
    }
}