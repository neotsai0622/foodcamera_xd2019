package com.example.a.foodcam;

import com.google.gson.Gson;

import java.util.Objects;

public class Food {
    public final static String[] nutrients = {"calories", "calories", "fat", "lipide",
            "sugar", "sucre", "protein", "protéine", "carbohydrate", "glucide", "sodium", "sodium"};
    private String name;
    private double calories;
    private double fat;

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
