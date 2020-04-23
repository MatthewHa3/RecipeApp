package com.example.recipeapp;

import com.example.recipeapp.Entities.Meals;

import java.util.List;

//abstract class used to group methods of CategoryView
//Methods will be implemented in CategoryActivity
public interface CategoryView {
    void showLoading();
    void hideLoading();
    void setMeals(List<Meals> meals);
}