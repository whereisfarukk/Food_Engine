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

        if (getIntent().getIntExtra("type", 0) == 1) {
            int image = getIntent().getIntExtra("image", 0);
            int price = Integer.parseInt(getIntent().getStringExtra("price"));
            String name = getIntent().getStringExtra("name");
            String description = getIntent().getStringExtra("description");

            binding.detailImage.setImageResource(image);
            binding.priceLbl.setText(String.format("%d", price));
            binding.foodName.setText(name);
            binding.detailDescription.setText(description);

            binding.insertButton.setOnClickListener(view -> {
                boolean isInserted = helper.insertOrder(
                        binding.nameBox.getText().toString(),
                        binding.phoneBox.getText().toString(),
                        price,
                        image,
                        name,
                        description,
                        Integer.parseInt(binding.quantity.getText().toString())
                );

                if (isInserted) {
                    Toast.makeText(this, "Order Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            int id = getIntent().getIntExtra("id", 0);
            Cursor cursor = helper.getOrderById(id);
            final int image = cursor.getInt(helper.getCursorIndex("image"));

            binding.detailImage.setImageResource(cursor.getInt(helper.getCursorIndex("image")));
            binding.priceLbl.setText(cursor.getString(helper.getCursorIndex("price")));
            binding.foodName.setText(cursor.getString(helper.getCursorIndex("foodName")));
            binding.detailDescription.setText(cursor.getString(helper.getCursorIndex("description")));
            binding.nameBox.setText(cursor.getString(helper.getCursorIndex("name")));
            binding.phoneBox.setText(cursor.getString(helper.getCursorIndex("phone")));
            binding.insertButton.setText("Update Now");

            binding.insertButton.setOnClickListener(view -> {
                boolean isUpdated = helper.updateOrder(
                        binding.nameBox.getText().toString(),
                        binding.phoneBox.getText().toString(),
                        Integer.parseInt(binding.priceLbl.getText().toString()),
                        image,
                        binding.detailDescription.getText().toString(),
                        binding.foodName.getText().toString(),
                        1,
                        id
                );

                if (isUpdated) {
                    Toast.makeText(DetailActivity.this, "Order Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
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