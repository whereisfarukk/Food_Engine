package com.example.food_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
            Toast.makeText(LoginActivity.this, "Please type your phone number and password!", Toast.LENGTH_SHORT).show();
        } else {
            DBHelper helper = new DBHelper(this);
            SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
            String[] projection = {"phone", "password"};
            String selection = "phone = ?";
            String[] selectionArgs = {phoneNumber};
            Cursor cursor = sqLiteDatabase.query("users", projection, selection, selectionArgs, null, null, null);
            if (cursor.moveToFirst()) {
                if (password.equals(cursor.getString(1))) {
                    Toast.makeText(LoginActivity.this, "Sign in successful!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong password!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "Phone number not registered, sign up instead!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}