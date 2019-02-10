package com.example.a.foodcam;

public class Food {
    private String name;
    private int calories;
    private int fat;
    private int sugars;

    public Food(String name, int calories, int fat, int sugars) {
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.sugars = sugars;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public int getFat() {
        return fat;
    }

    public int getSugars() {
        return sugars;
    }

}
