package com.example.recipeapp.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MealLoreResponse implements Serializable {

    @SerializedName("meals")
    @Expose
    private List<Meals> meals = new ArrayList<>();

    public MealLoreResponse(){
    }

    public MealLoreResponse(List<Meals> meals){
        super();
        this.meals = meals;
    }

    public List<Meals> getMeals() {
        return meals;
    }

    public void setMeals(List<Meals> meals) {
        this.meals = meals;
    }
}
