package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.example.recipeapp.Entities.Categories;
import com.example.recipeapp.Entities.Meals;
import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MainView{
    private String TAG = "MainActivity";
    private MainView view;
    private VphAdapter headerAdapter;
    private CategoryAdapter categoryAdapter;

    public MainActivity(MainView view) {
        this.view = view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view.showLoading();
        new GetMealTask().execute();
        new GetCategoryTask().execute();
    }

    @Override
    public void showLoading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.VISIBLE);
        findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.GONE);
        findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
    }

    @Override
    public void setMeal(List<Meals.Meal> meal) {
        headerAdapter = new VphAdapter(meal, this);
        ViewPager viewPagerMeal = findViewById(R.id.vpHeader);
        viewPagerMeal.setAdapter(headerAdapter);
    }

    @Override
    public void setCategory(List<Categories.Category> category) {
        categoryAdapter = new CategoryAdapter(category, this);
        RecyclerView recyclerViewCategory = findViewById(R.id.rvCategory);
        recyclerViewCategory.setAdapter(categoryAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);
    }

    private class GetMealTask extends AsyncTask<Void, Void, List<Meals.Meal>> {
        @Override
        protected List<Meals.Meal> doInBackground(Void... voids) {
            try {
                Log.d(TAG, "onSuccess: SUCCESS");
                view.hideLoading();
                //Create Retrofit instance and parse the retrieved Json using Gson deserialiser
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1").addConverterFactory(GsonConverterFactory.create()).build();
                //Get Service and call object for the request
                RecipeService service = retrofit.create(RecipeService.class);
                Call<Meals> mealsCall = service.getMeals();

                //Execute network request
                Response<Meals> mealResponse = mealsCall.execute();
                List<Meals.Meal> meals = mealResponse.body().getMeals();
                setMeal(meals);
                return meals;
            } catch (IOException e) {
                Log.d(TAG, "onFailue: FAILURE");
                view.hideLoading();
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Meals.Meal> meals) { headerAdapter.setMeals(meals); }

    }

    private class GetCategoryTask extends AsyncTask<Void, Void, List<Categories.Category>> {
        @Override
        protected List<Categories.Category> doInBackground(Void... voids) {
            try {
                Log.d(TAG, "onSuccess: SUCCESS");
                view.hideLoading();
                //Create Retrofit instance and parse the retrieved Json using Gson deserialiser
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1").addConverterFactory(GsonConverterFactory.create()).build();
                //Get Service and call object for the request
                RecipeService service = retrofit.create(RecipeService.class);
                Call<Categories> categoriesCall = service.getCategories();

                //Execute network request
                Response<Categories> categoriesResponse = categoriesCall.execute();
                List<Categories.Category> categories = categoriesResponse.body().getCategories();
                setCategory(categories);
                return categories;
            } catch (IOException e) {
                Log.d(TAG, "onFailue: FAILURE");
                view.hideLoading();
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Categories.Category> categories) { categoryAdapter.setCategories(categories); }

    }
}
