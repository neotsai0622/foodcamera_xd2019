package com.example.a.foodcam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EditorActivity extends AppCompatActivity {
    Button button;
    TextView foodName;
    Food food;
    TextView calories;
    TextView fat;
    TextView sugars;
    TextView protein;
    TextView carbohydrates;
    TextView sodium;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        button = findViewById(R.id.submit);
        foodName = findViewById(R.id.enter_name);
        calories = findViewById(R.id.calories_entry);
        fat = findViewById(R.id.fat_entry);
        sugars = findViewById(R.id.sugars_entry);
        protein = findViewById(R.id.protein_entry);
        carbohydrates = findViewById(R.id.carbohydrate_entry);
        sodium = findViewById(R.id.sodium_entry);


        Intent intent = getIntent();
        String desc = intent.getStringExtra("description");
        try {
            food = LabelParser.jsonToFood(desc, "");
            fat.setText(String.valueOf(food.getFat()));
            calories.setText(String.valueOf(food.getCalories()));
            sugars.setText(String.valueOf(food.getSugars()));
            protein.setText(String.valueOf(food.getProtein()));
            carbohydrates.setText(String.valueOf(food.getCarbohydrate()));
            sodium.setText(String.valueOf(food.getSodium()));
        } catch (BadImageException e) {
            setResult(RESULT_FIRST_USER, new Intent());
            finish();
        }
    }

    public void onClick(View view) {

    }
}
