package com.example.recipeapp;

import com.example.recipeapp.Entities.Categories;
import com.example.recipeapp.Entities.Meals;

import java.util.List;

//abstract class used to group methods of MainView
//Methods will be implemented in MainActivity
public interface MainView {

    void showLoading();
    void hideLoading();
    void setMeal(List<Meals> meal);
    void setCategory(List<Categories> category);

}