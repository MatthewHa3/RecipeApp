package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.recipeapp.Entities.Categories;
import com.example.recipeapp.Entities.CategoryLoreResponse;
import com.example.recipeapp.Entities.MealLoreResponse;
import com.example.recipeapp.Entities.Meals;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{
    private String TAG = "MainActivity";
    private VphAdapter headerAdapter;
    private CategoryAdapter categoryAdapter;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.profileButton);
        headerAdapter = new VphAdapter(new ArrayList<>(), this);
        ViewPager viewPagerMeal = findViewById(R.id.vpHeader);
        viewPagerMeal.setAdapter(headerAdapter);
        Log.d(TAG, "vph set");
        categoryAdapter = new CategoryAdapter(new ArrayList<>(), this);
        RecyclerView recyclerViewCategory = findViewById(R.id.rvCategory);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerViewCategory.setLayoutManager(mLayoutManager);
        recyclerViewCategory.setHasFixedSize(true);;
        recyclerViewCategory.setAdapter(categoryAdapter);
        Log.d(TAG, "rc set");
        //Create Retrofit instance and parse the retrieved Json using Gson deserialiser
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").addConverterFactory(GsonConverterFactory.create()).build();
        //Get Service and call object for the request
        RecipeService service = retrofit.create(RecipeService.class);
        Call<MealLoreResponse> mealsCall = service.getMeals();
        mealsCall.enqueue(new Callback<MealLoreResponse>() {
            @Override
            public void onResponse(Call<MealLoreResponse> call, Response<MealLoreResponse> response) {
                Log.d(TAG, "onSuccess: SUCCESS");
                List<Meals> meals = response.body().getMeals();
                setMeal(meals);
            }

            @Override
            public void onFailure(Call<MealLoreResponse> call, Throwable t) {

            }
        });

        Call<CategoryLoreResponse> categoriesCall = service.getCategories();
        categoriesCall.enqueue(new Callback<CategoryLoreResponse>() {
            @Override
            public void onResponse(Call<CategoryLoreResponse> call, Response<CategoryLoreResponse> response) {
                Log.d(TAG, "onSuccess: SUCCESS");
                List<Categories> categories = response.body().getCategories();
                setCategory(categories);
            }

            @Override
            public void onFailure(Call<CategoryLoreResponse> call, Throwable t) {

            }
        });
        mButton.setOnClickListener((view) -> {
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
        });

    }

    public MainActivity(){
    }

    public void setMeal(List<Meals> meal) {
        headerAdapter = new VphAdapter(meal, this);
        ViewPager viewPagerMeal = findViewById(R.id.vpHeader);
        viewPagerMeal.setAdapter(headerAdapter);
        headerAdapter.setMeals(meal);
        headerAdapter.setOnItemClickListener((view, position) -> {
            TextView mealName = view.findViewById(R.id.mealName);
            Intent intent = new Intent(getApplicationContext(), RecipeActivity.class);
            intent.putExtra(RecipeActivity.EXTRA_RECIPE, mealName.getText().toString());
            startActivity(intent);
        });
    }

    public void setCategory(List<Categories> category) {
        categoryAdapter = new CategoryAdapter(category, this);
        RecyclerView recyclerViewCategory = findViewById(R.id.rvCategory);
        recyclerViewCategory.setAdapter(categoryAdapter);
        categoryAdapter.setCategories(category);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        categoryAdapter.setOnItemClickListener((view1, position) -> {
            Log.d(TAG, "clicked");
            Intent intent = new Intent(this, CategoryActivity.class);
            intent.putExtra(CategoryActivity.EXTRA_CATEGORY, (Serializable) category);
            intent.putExtra(CategoryActivity.EXTRA_POSITION, position);
            startActivity(intent);
        });
    }
}