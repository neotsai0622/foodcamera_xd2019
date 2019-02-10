package com.example.a.foodcam.db;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Nutrition {
    @PrimaryKey
    @NonNull
    public double calories;

    public double fat;

    public double sodium;

    public double carbohydrate;

    public double sugar;

    public double protein;
}
