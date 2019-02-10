package com.example.a.foodcam.db;

import android.content.Context;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Nutrition.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    private static AppDatabase INSTANCE;

    public abstract NutritionDao nutritionModel();

    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null ) {
            INSTANCE =
                    Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
