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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a.foodcam.*;
import com.example.a.foodcam.db.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class SummaryActivity extends AppCompatActivity {
    List<Food> foods;
    NutritionAdapter adapter;
    Button clearButton;

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
        //foods.add(0, new Food("Juice", 100, 0, 23, 0, 50, 0));

        // Create adapter passing in the sample user data
        adapter = new NutritionAdapter(foods);
        // Attach the adapter to the recyclerview to populate items
        rvNutrition.setAdapter(adapter);
        // Set layout manager to position the items
        rvNutrition.setLayoutManager(new LinearLayoutManager(this));
        // That's all!

        adapter.notifyDataSetChanged();

        double datas[] = {0, 0, 0, 0, 0, 0};
        for (int i = 0; i < foods.size(); i++) {
            datas[0] += foods.get(i).getCalories();
            datas[1] += foods.get(i).getFat();
            datas[2] += foods.get(i).getSodium();
            datas[3] += foods.get(i).getCarbohydrate();
            datas[4] += foods.get(i).getSugars();
            datas[5] += foods.get(i).getProtein();
        }

        TextView cals = findViewById(R.id.calories);
        TextView fat = findViewById(R.id.fat);
        TextView sod = findViewById(R.id.sodium);
        TextView carb = findViewById(R.id.carbs);
        TextView sug = findViewById(R.id.sugar);
        TextView prot = findViewById(R.id.protein);
        String Cals = "Calories" + "\n"+round(datas[0], 2);
        String Fat = "Fat" + "\n"+round(datas[1],2 );
        String Sod = "Sodium" + "\n"+round(datas[2], 2);
        String Carbs = "Carbs" + "\n"+round(datas[3], 2);
        String Sug = "Sugar" + "\n"+round(datas[4],2 );
        String Prot = "Protein" + "\n"+round(datas[5], 2);

        cals.setText(Cals);
        fat.setText(Fat);
        sod.setText(Sod);
        carb.setText(Carbs);
        sug.setText(Sug);
        prot.setText(Prot);

        clearButton = findViewById(R.id.clearbutton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase.getInMemoryDatabase(getApplicationContext()).FoodModel().deleteAll();
                AppDatabase.destroyInstance();
                adapter.notifyDataSetChanged();
                finish();
            }
        });
    }

    private void addItem(Food food) {
        RecyclerView rvNutrition = findViewById(R.id.rvNutrition);
        foods.add(1, food);

        adapter.notifyItemInserted(1);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}

//