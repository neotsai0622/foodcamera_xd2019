package com.example.a.foodcam;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.a.foodcam.R;
import com.example.a.foodcam.db.AppDatabase;
import com.example.a.foodcam.db.Nutrition;
import com.example.a.foodcam.db.utils.DatabaseInitializer;

import java.util.List;
import java.util.Locale;

public class trialactivity extends AppCompatActivity {

    private AppDatabase mDb;

    private TextView mYoungUsersTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mYoungUsersTextView = findViewById(R.id.message);

        mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());

        populateDb();

        fetchData();
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    private void populateDb() {
        DatabaseInitializer.populateSync(mDb);
    }

    private void fetchData() {
        StringBuilder sb = new StringBuilder();
        List<Nutrition> foods = mDb.nutritionModel().loadAllNutrition();
        for (Nutrition n: foods ) {
            sb.append(String.format(Locale.US, "Cal:%f, Fat:%f, Sodium:%f, " +
                    "Carbohydrate:%f, Sugar:%f, Protein:%f. \n",n.calories, n.fat, n.sodium, n.carbohydrate, n.sugar, n.protein));
        }
        mYoungUsersTextView.setText(sb);
    }


}
