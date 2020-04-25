package com.example.recipeapp;

import com.example.recipeapp.Entities.Meals;

//abstract class used to group methods of RecipeView
//Methods will be implemented in RecipeActivity
public interface RecipeView {
    void showLoading();
    void hideLoading();
    void setMeal(Meals meal);
}
