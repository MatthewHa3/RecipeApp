package com.example.recipeapp;

import com.example.recipeapp.Entities.Categories;
import com.example.recipeapp.Entities.Meals;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeService {
    @GET("/search.php?f=a")
    Call<Meals> getMeals();

    @GET("/categories.php")
    Call<Categories> getCategories();
}
