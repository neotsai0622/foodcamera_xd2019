package com.example.a.foodcam;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.Objects;

@Entity
public class Food implements Parcelable {
    public final static String[] nutrients = {"calories", "calories", "fat", "lipide",
            "sugar", "sucre", "protein", "protéine", "carbohydrate", "glucide", "sodium", "sodium"};
    @PrimaryKey
    @NonNull
    private String name;
    private double calories;
    private double fat;
    private double sugars;
    private double protein;
    private double carbohydrate;
    private double sodium;

    public Food(String name, double calories, double fat, double sugars, double protein, double carbohydrate, double sodium) {
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.sugars = sugars;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.sodium = sodium;
    }

    public Food(Parcel in) {
        String[] data= new String[7];

        in.readStringArray(data);
        this.name = data[0];
        this.calories = Double.parseDouble(data[1]);
        this.fat = Double.parseDouble(data[2]);
        this.sugars = Double.parseDouble(data[3]);
        this.protein = Double.parseDouble(data[4]);
        this.carbohydrate = Double.parseDouble(data[5]);
        this.sodium = Double.parseDouble(data[6]);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.name, String.valueOf(this.calories), String.valueOf(this.fat),
                String.valueOf(this.sugars), String.valueOf(this.protein), String.valueOf(this.carbohydrate),
                String.valueOf(this.sodium)});
    }

    public static final Parcelable.Creator<Food> CREATOR = new Parcelable.Creator<Food>() {

        @Override
        public Food createFromParcel(Parcel source) {
            return new Food(source);  //using parcelable constructor
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };



    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public void setSugars(double sugars) {
        this.sugars = sugars;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }



    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }

    public double getFat() {
        return fat;
    }

    public double getSugars() {
        return sugars;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public double getSodium() {
        return sodium;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return getCalories() == food.getCalories() &&
                getFat() == food.getFat() &&
                getSugars() == food.getSugars() &&
                getProtein() == food.getProtein() &&
                getCarbohydrate() == food.getCarbohydrate() &&
                getSodium() == food.getSodium() &&
                Objects.equals(getName(), food.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getCalories(), getFat(), getSugars(), getProtein(), getCarbohydrate(), getSodium());
    }
}
