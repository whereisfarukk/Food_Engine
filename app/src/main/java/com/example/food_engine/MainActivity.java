package com.example.food_engine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.food_engine.Adapters.MainAdapter;
import com.example.food_engine.Models.MainModel;
import com.example.food_engine.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater()); // binding is used replacement of findViewById
        setContentView(binding.getRoot());

        ArrayList<MainModel>list=new ArrayList<>();
        list.add(new MainModel(R.drawable.cheese_burger,"Cheese Burger", "4","Chicken Burger with extra cheese"));
        list.add(new MainModel(R.drawable.sandwitch,"Sandwich", "2","A sandwich is a food typically consisting of vegetables, sliced cheese or meat, placed on or between slices of bread"));
        list.add(new MainModel(R.drawable.pizza,"Pizza", "4","Pizza, dish of Italian origin consisting of a flattened disk of bread dough topped with some combination of olive oil, oregano, tomato, olives, mozzarella or other cheese"));
        list.add(new MainModel(R.drawable.french_fries,"French fries", "3","French fries typically made from deep-fried potatoes that have been cut into various shapes, especially thin strips."));
        list.add(new MainModel(R.drawable.noodless,"Noodles", "1","Noodles is very popular dish in the whole world"));
        list.add(new MainModel(R.drawable.hotdog,"Cheese Burger", "2","A hot dog is a food consisting of a grilled or steamed sausage served in the slit of a partially sliced bun."));
        list.add(new MainModel(R.drawable.chicken_fry,"Fried chicken", "5","Fried chicken, also known as Southern fried chicken, is a dish consisting of chicken pieces that have been coated with seasoned flour or batter"));
        list.add(new MainModel(R.drawable.chips,"Chips", "2","Chips are long, thin pieces of potato fried in oil or fat and eaten hot, usually with a meal."));
        list.add(new MainModel(R.drawable.pankake,"pancake", "1.5","A pancake is a thin, flat, circular piece of cooked batter made from milk, flour, and eggs."));
        list.add(new MainModel(R.drawable.cookies,"Cookies", "2","A cookie is a baked or cooked snack or dessert that is typically small, flat and sweet."));


        MainAdapter adapter=new MainAdapter(list,this);
        binding.recyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);

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
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}