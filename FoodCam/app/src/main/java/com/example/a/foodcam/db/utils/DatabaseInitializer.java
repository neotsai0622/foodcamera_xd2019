package com.example.a.foodcam.db.utils;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.a.foodcam.db.AppDatabase;
import com.example.a.foodcam.db.Nutrition;


public class DatabaseInitializer {

    private static final int DELAY_MILLIS = 500;

    public static void populateAsync(final AppDatabase db) {

        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync (@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

    private static Nutrition addNutrition(final AppDatabase db, final double calories,
                                    final double fat, final double sodium, final double carbohydrates,
                                    final double sugar, final double protein) {

        Nutrition nutrition = new Nutrition();

        nutrition.calories = calories;
        nutrition.fat = fat;
        nutrition.sodium = sodium;
        nutrition.carbohydrate = carbohydrates;
        nutrition.sugar = sugar;
        nutrition.protein = protein;

        return nutrition;
    }

    private static void populateWithTestData(AppDatabase db) {
        db.nutritionModel().deleteAll();

        addNutrition(db, 100, 10, 20, 30, 40, 50);
        addNutrition(db, 1000, 100, 200, 300, 400, 500);
        addNutrition(db, 1,2, 3, 4, 5, 6);

    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }
    }
}
