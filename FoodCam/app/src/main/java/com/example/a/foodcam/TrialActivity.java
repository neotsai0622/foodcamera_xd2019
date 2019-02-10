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

        Food food = getIntent().getParcelableExtra("Food");
        populateDb(food);
        fetchData();
    }

    //    @Override
    //    protected void onDestroy() {
    //        AppDatabase.destroyInstance();
    //        super.onDestroy();
    //    }

    private void populateDb(Food food) {
        mDb.FoodModel().insertFood(food);
    }

    private void fetchData() {
        StringBuilder sb = new StringBuilder();
        List<Food> foods = mDb.FoodModel().loadAllFood();
        for (Food n: foods) {
            sb.append(n.getName());
            sb.append(":\t");
            sb.append(String.format(Locale.US, "Cal: %.2f, Fat: %.2f, Sodium: %.2f, " +
                    "Carbohydrate: %.2f, Sugar: %.2f, Protein: %.2f. \n",n.getCalories(), n.getFat(),
                    n.getSodium(), n.getCarbohydrate(), n.getSugars(), n.getProtein()));
            sb.append("\n");
        }
        mYoungUsersTextView.setText(sb);
    }


}
