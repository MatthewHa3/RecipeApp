package com.example.recipeapp;

import com.example.recipeapp.Entities.Meals;

public interface RecipeView {
    void showLoading();
    void hideLoading();
    void setMeal(Meals meal);
}
