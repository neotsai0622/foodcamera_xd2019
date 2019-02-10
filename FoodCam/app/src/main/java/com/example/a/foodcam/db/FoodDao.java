package com.example.a.foodcam.db;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import com.example.a.foodcam.Food;

import java.util.Date;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


@Dao
public interface FoodDao {
    @Query("select * from Food")
    List<Food> loadAllFood();

    @Insert(onConflict = IGNORE)
    void insertFood(Food input);

    @Delete
    void deleteFood(Food input1, Food input2);

    @Query("DELETE FROM Food")
    void deleteAll();

}
