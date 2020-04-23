package com.example.recipeapp;

import android.util.Log;

import com.example.recipeapp.Entities.MealLoreResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CategoryPresenter {
    private CategoryView view;

    public CategoryPresenter(CategoryView view) {
        this.view = view;
    }

    public void getMealByCategory(String category){
        view.showLoading();
        //Create Retrofit instance and parse the retrieved Json using Gson deserialiser
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").addConverterFactory(GsonConverterFactory.create()).build();
        //Get Service and call object for the request
        RecipeService service = retrofit.create(RecipeService.class);
        Call<MealLoreResponse> mealsCall = service.getMealByCategory(category);
        mealsCall.enqueue(new Callback<MealLoreResponse>() {
            @Override
            public void onResponse(Call<MealLoreResponse> call, Response<MealLoreResponse> response) {
                Log.d(TAG, "onSuccess: SUCCESS");
                view.hideLoading();
                view.setMeals(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<MealLoreResponse> call, Throwable t){
                view.hideLoading();
                Log.d(TAG, "onFailure: FAILURE");
            }
        });
    }
}
