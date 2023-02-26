package com.example.food_engine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.os.Bundle;
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
        list.add(new MainModel(R.drawable.burger,"Burger", "15","Chicken Burger with extra cheese"));
        list.add(new MainModel(R.drawable.burger,"Burger", "15","Chicken Burger with extra cheese"));
        list.add(new MainModel(R.drawable.burger,"Burger", "15","Chicken Burger with extra cheese"));
        list.add(new MainModel(R.drawable.burger,"Burger", "15","Chicken Burger with extra cheese"));
        list.add(new MainModel(R.drawable.burger,"Burger", "15","Chicken Burger with extra cheese"));
        list.add(new MainModel(R.drawable.burger,"Burger", "15","Chicken Burger with extra cheese"));
        list.add(new MainModel(R.drawable.burger,"Burger", "15","Chicken Burger with extra cheese"));
        list.add(new MainModel(R.drawable.burger,"Burger", "15","Chicken Burger with extra cheese"));
        list.add(new MainModel(R.drawable.burger,"Burger", "15","Chicken Burger with extra cheese"));

        MainAdapter adapter=new MainAdapter(list,this);
        binding.recyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);

    }
}