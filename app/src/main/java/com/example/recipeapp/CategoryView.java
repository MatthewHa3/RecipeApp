package com.example.recipeapp;

import com.example.recipeapp.Entities.Meals;

import java.util.List;

public interface CategoryView {
    void showLoading();
    void hideLoading();
    void setMeals(List<Meals> meals);
}
