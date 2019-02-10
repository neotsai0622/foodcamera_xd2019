package com.example.a.foodcam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.a.foodcam.db.AppDatabase;

import java.util.List;
import java.util.Locale;

public class TrialActivity extends AppCompatActivity {

    private AppDatabase mDb;

    private TextView mYoungUsersTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_trial);

        mYoungUsersTextView = findViewById(R.id.message);

        mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());
        Log.d("lookforthistag", getIntent().toString());
        Log.d("lookforthistag", getIntent().getParcelableExtra("Food").toString());


        Food food = getIntent().getParcelableExtra("Food");
        populateDb(food);
        fetchData();
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    private void populateDb(Food food) {
        Log.d("lookforthistag", food.toString());
        mDb.FoodModel().insertFood(food);
    }

    private void fetchData() {
        StringBuilder sb = new StringBuilder();
        List<Food> foods = mDb.FoodModel().loadAllFood();
        for (Food n: foods) {
            sb.append(String.format(Locale.US, "Cal:%f, Fat:%f, Sodium:%f, " +
                    "Carbohydrate:%f, Sugar:%f, Protein:%f. \n",n.getCalories(), n.getFat(),
                    n.getSodium(), n.getCarbohydrate(), n.getSugars(), n.getProtein()));
        }
        mYoungUsersTextView.setText(sb);
    }


}
