package com.example.recipeapp;

import com.example.recipeapp.Entities.CategoryLoreResponse;
import com.example.recipeapp.Entities.MealLoreResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeService {
    @GET("latest.php")
    Call<MealLoreResponse> getMeals();

    @GET("categories.php")
    Call<CategoryLoreResponse> getCategories();
}
