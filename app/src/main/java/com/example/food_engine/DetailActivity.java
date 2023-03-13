package com.example.food_engine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.food_engine.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final DBHelper helper = new DBHelper(this);

        binding.subtract.setOnClickListener(view -> {
            int quantity = Integer.parseInt(binding.quantity.getText().toString());
            if (quantity > 1) {
                quantity--;
            }
            binding.quantity.setText(quantity + "");
        });

        binding.add.setOnClickListener(view -> {
            int quantity = Integer.parseInt(binding.quantity.getText().toString()) + 1;
            binding.quantity.setText(quantity + "");
        });

        if (getIntent().getIntExtra("type", 0) == 1) {
            int image = getIntent().getIntExtra("image", 0);
            int price = Integer.parseInt(getIntent().getStringExtra("price"));
            String foodName = getIntent().getStringExtra("foodName");
            String description = getIntent().getStringExtra("description");
            binding.detailImage.setImageResource(image);
            binding.priceLbl.setText(price + "");
            binding.foodName.setText(foodName);
            binding.detailDescription.setText(description);

            binding.insertButton.setOnClickListener(view -> {
                String customerName = binding.nameBox.getText().toString();
                String phoneNumber = binding.phoneBox.getText().toString();
                if (customerName.isEmpty() || phoneNumber.isEmpty()) {
                    Toast.makeText(DetailActivity.this, "Name and phone number must be given", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isInserted = helper.insertOrder(
                            customerName,
                            phoneNumber,
                            price,
                            image,
                            foodName,
                            description,
                            Integer.parseInt(binding.quantity.getText().toString())
                    );
                    if (isInserted) {
                        Toast.makeText(this, "Order Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DetailActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            int id = getIntent().getIntExtra("id", 0);
//            Toast.makeText(DetailActivity.this, id + "", Toast.LENGTH_SHORT).show();
            Cursor cursor = helper.getOrderById(id);

            binding.detailImage.setImageResource(cursor.getInt(helper.getCursorIndex("image")));
            binding.priceLbl.setText(cursor.getString(helper.getCursorIndex("price")));
            binding.foodName.setText(cursor.getString(helper.getCursorIndex("foodName")));
            binding.detailDescription.setText(cursor.getString(helper.getCursorIndex("description")));
            binding.nameBox.setText(cursor.getString(helper.getCursorIndex("name")));
            binding.phoneBox.setText(cursor.getString(helper.getCursorIndex("phone")));
            binding.quantity.setText(cursor.getInt(helper.getCursorIndex("quantity")) + "");
            binding.insertButton.setText("Update Now");

            binding.insertButton.setOnClickListener(view -> {
                String customerName = binding.nameBox.getText().toString();
                String phoneNumber = binding.phoneBox.getText().toString();
                if (customerName.isEmpty() || phoneNumber.isEmpty()) {
                    Toast.makeText(DetailActivity.this, "Name and phone number must be given", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isUpdated = helper.updateOrder(
                            customerName,
                            phoneNumber,
                            Integer.parseInt(binding.quantity.getText().toString()),
                            id
                    );
                    if (isUpdated) {
                        Toast.makeText(DetailActivity.this, "Order Updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.orders:
                startActivity(new Intent(DetailActivity.this, OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}