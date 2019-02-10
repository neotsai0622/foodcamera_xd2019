package com.example.a.foodcam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.a.foodcam.*;
import com.example.a.foodcam.db.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class SummaryActivity extends AppCompatActivity {
    List<Food> foods;
    NutritionAdapter adapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(SummaryActivity.this, MainActivity.class));
                    return true;
                case R.id.navigation_dashboard:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        // ...
        // Lookup the recyclerview in activity layout
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        RecyclerView rvNutrition = (RecyclerView) findViewById(R.id.rvNutrition);

        // Initialize contacts
        foods = AppDatabase.getInMemoryDatabase(getApplicationContext()).FoodModel().loadAllFood();
        foods.add(0, new Food("Juice", 100, 0, 23, 0, 50, 0));
        foods.add(0, new Food("Juice", 100, 0, 23, 0, 50, 0));


        // Create adapter passing in the sample user data
        adapter = new NutritionAdapter(foods);
        // Attach the adapter to the recyclerview to populate items
        rvNutrition.setAdapter(adapter);
        // Set layout manager to position the items
        rvNutrition.setLayoutManager(new LinearLayoutManager(this));
        // That's all!

        adapter.notifyDataSetChanged();


    }

    private void addItem(Food food) {
        RecyclerView rvNutrition = findViewById(R.id.rvNutrition);
        foods.add(1, food);

        adapter.notifyItemInserted(1);
    }
}
//