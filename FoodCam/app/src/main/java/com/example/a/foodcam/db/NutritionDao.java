package com.example.a.foodcam.db;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import java.util.Date;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


@Dao
public interface NutritionDao {
    @Query("select * from nutrition")
    List<Nutrition> loadAllNutrition();

    @Insert(onConflict = IGNORE)
    void insertNutrition(Nutrition input);

    @Delete
    void deleteNutrition(Nutrition input1, Nutrition input2);

    @Query("DELETE FROM Nutrition")
    void deleteAll();
}
