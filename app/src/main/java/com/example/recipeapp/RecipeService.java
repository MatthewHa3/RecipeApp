package com.example.recipeapp;

import com.example.recipeapp.Entities.CategoryLoreResponse;
import com.example.recipeapp.Entities.MealLoreResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeService {
    @GET("search.php?f=a")
    Call<MealLoreResponse> getMeals();

    @GET("categories.php")
    Call<CategoryLoreResponse> getCategories();

    @GET("filter.php")
    Call<MealLoreResponse> getMealByCategory(@Query("c") String category);

    @GET("search.php")
    Call<MealLoreResponse> getMealByName(@Query("s") String mealName);
}
