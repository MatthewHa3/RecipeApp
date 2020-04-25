package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.recipeapp.Entities.MealLoreResponse;
import com.example.recipeapp.Entities.Meals;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeActivity extends AppCompatActivity implements RecipeView{
    private String TAG = "RecipeActivity";
    public static final String EXTRA_RECIPE = "recipe";
    private CollapsingToolbarLayout mCTL;
    private ImageView mThumb;
    private TextView mCategory, mCountry, mInstructions, mIngredients, mMeasures, mYoutube, mSource, mComplete;
    private ProgressBar mProgressBar;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        mCTL = findViewById(R.id.collapsing_toolbar);
        mThumb = findViewById(R.id.mealThumb);
        mCategory = findViewById(R.id.tvCategory);
        mCountry = findViewById(R.id.tvCountry);
        mInstructions = findViewById(R.id.tvInstructions);
        mIngredients = findViewById(R.id.tvIngredient);
        mMeasures = findViewById(R.id.tvMeasure);
        mProgressBar = findViewById(R.id.progressBar);
        mYoutube = findViewById(R.id.youtube);
        mSource = findViewById(R.id.source);
        mButton = findViewById(R.id.completeButton);
        mComplete = findViewById(R.id.tvComplete);
        Intent intent = getIntent();
        String mealName = intent.getStringExtra(EXTRA_RECIPE);
        getMealById(mealName);
        showLoading();
        SharedPreferences sharedPoints = getSharedPreferences("POINTS", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPoints.edit();
        mButton.setOnClickListener(v -> {
            if (mCategory.getText().toString().equals("Beef") || mCategory.getText().toString().equals("Chicken") || mCategory.getText().toString().equals("Dessert")
                    || mCategory.getText().toString().equals("Lamb") || mCategory.getText().toString().equals("Pork") || mCategory.getText().toString().equals("Seafood")
                    || mCategory.getText().toString().equals("Vegan") || mCategory.getText().toString().equals("Goat")){
                int currentPoints  = sharedPoints.getInt("points", 0);
                myEdit.putInt("points", currentPoints + 100);
                myEdit.commit();
                int a = sharedPoints.getInt("points", 0);
                Log.d(TAG, String.valueOf(a));
                mComplete.setText("Successfully completed recipe");
            } else if (mCategory.getText().toString().equals("Vegetarian")){
                int currentPoints  = sharedPoints.getInt("points", 0);
                myEdit.putInt("points", currentPoints + 80);
                myEdit.commit();
                int a = sharedPoints.getInt("points", 0);
                Log.d(TAG, String.valueOf(a));
                mComplete.setText("Successfully completed recipe");
            } else if (mCategory.getText().toString().equals("Miscellaneous") || mCategory.getText().toString().equals("Pasta")){
                int currentPoints  = sharedPoints.getInt("points", 0);
                myEdit.putInt("points", currentPoints + 60);
                myEdit.commit();
                int a = sharedPoints.getInt("points", 0);
                Log.d(TAG, String.valueOf(a));
                mComplete.setText("Successfully completed recipe");
            } else if (mCategory.getText().toString().equals("Starter") || mCategory.getText().toString().equals("Breakfast")){
                int currentPoints  = sharedPoints.getInt("points", 0);
                myEdit.putInt("points", currentPoints + 50);
                myEdit.commit();
                int a = sharedPoints.getInt("points", 0);
                Log.d(TAG, String.valueOf(a));
                mComplete.setText("Successfully completed recipe");
            } else if (mCategory.getText().toString().equals("Side")){
                int currentPoints  = sharedPoints.getInt("points", 0);
                myEdit.putInt("points", currentPoints + 40);
                myEdit.commit();
                int a = sharedPoints.getInt("points", 0);
                Log.d(TAG, String.valueOf(a));
                mComplete.setText("Successfully completed recipe");
            } else {
                mComplete.setText("Fail");
            }
        });
    }
    @Override
    public void showLoading(){
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading(){
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setMeal(Meals meal){
        mCTL.setTitle(meal.getStrMeal());
        mCTL.setCollapsedTitleTextColor(getResources().getColor(R.color.colorWhite));
        mCTL.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));
        Glide.with(mThumb).load(meal.getStrMealThumb()).fitCenter().into(mThumb);
        mCategory.setText(meal.getStrCategory());
        mCountry.setText(meal.getStrArea());
        mInstructions.setText(meal.getStrInstructions());

        if (meal.getStrIngredient1() != null) {
            mIngredients.append("\n \u2022 " + meal.getStrIngredient1());
        }
        if (meal.getStrIngredient2() != null) {
            mIngredients.append("\n \u2022 " + meal.getStrIngredient2());
        }
        if (meal.getStrIngredient3() != null) {
            mIngredients.append("\n \u2022 " + meal.getStrIngredient3());
        }
        if (meal.getStrIngredient4() != null) {
            mIngredients.append("\n \u2022 " + meal.getStrIngredient4());
        }
        if (meal.getStrIngredient5() != null) {
            mIngredients.append("\n \u2022 " + meal.getStrIngredient5());
        }
        if (meal.getStrIngredient6() != null) {
            mIngredients.append("\n \u2022 " + meal.getStrIngredient6());
        }
        if (meal.getStrIngredient7() != null) {
            mIngredients.append("\n \u2022 " + meal.getStrIngredient7());
        }
        if (meal.getStrIngredient8() != null) {
            mIngredients.append("\n \u2022 " + meal.getStrIngredient8());
        }
        if (meal.getStrIngredient9() != null) {
            mIngredients.append("\n \u2022 " + meal.getStrIngredient9());
        }
        if (meal.getStrIngredient10() != null) {
            mIngredients.append("\n \u2022 " + meal.getStrIngredient10());
        }
        if (meal.getStrIngredient11() != null) {
            mIngredients.append("\n \u2022 " + meal.getStrIngredient11());
        }
        if (meal.getStrIngredient12() != null) {
            mIngredients.append("\n \u2022 " + meal.getStrIngredient12());
        }
        if (meal.getStrIngredient13() != null) {
            mIngredients.append("\n \u2022 " + meal.getStrIngredient13());
        }
        if (meal.getStrIngredient14() != null) {
            mIngredients.append("\n \u2022 " + meal.getStrIngredient14());
        }
        if (meal.getStrIngredient15() != null) {
            mIngredients.append("\n \u2022 " + meal.getStrIngredient15());
        }
        if (meal.getStrIngredient16() != null) {
            mIngredients.append("\n \u2022 " + meal.getStrIngredient16());
        }
        if (meal.getStrIngredient17() != null) {
            mIngredients.append("\n \u2022 " + meal.getStrIngredient17());
        }
        if (meal.getStrIngredient18() != null) {
            mIngredients.append("\n \u2022 " + meal.getStrIngredient18());
        }
        if (meal.getStrIngredient19() != null) {
            mIngredients.append("\n \u2022 " + meal.getStrIngredient19());
        }
        if (meal.getStrIngredient20() != null) {
            mIngredients.append("\n \u2022 " + meal.getStrIngredient20());
        }

        if (meal.getStrMeasure1()!= null || meal.getStrMeasure1() == "") {
            mMeasures.append("\n : " + meal.getStrMeasure1());
        }
        if (meal.getStrMeasure2()!= null || meal.getStrMeasure2() == "") {
            mMeasures.append("\n : " + meal.getStrMeasure2());
        }
        if (meal.getStrMeasure3()!= null || meal.getStrMeasure3() == "") {
            mMeasures.append("\n : " + meal.getStrMeasure3());
        }
        if (meal.getStrMeasure4()!= null || meal.getStrMeasure3() == "") {
            mMeasures.append("\n : " + meal.getStrMeasure4());
        }
        if (meal.getStrMeasure5()!= null || meal.getStrMeasure5() == "") {
            mMeasures.append("\n : " + meal.getStrMeasure5());
        }
        if (meal.getStrMeasure6()!= null || meal.getStrMeasure6() == "") {
            mMeasures.append("\n : " + meal.getStrMeasure6());
        }
        if (meal.getStrMeasure7()!= null || meal.getStrMeasure7() == "") {
            mMeasures.append("\n : " + meal.getStrMeasure7());
        }
        if (meal.getStrMeasure8()!= null || meal.getStrMeasure8() == "") {
            mMeasures.append("\n : " + meal.getStrMeasure8());
        }
        if (meal.getStrMeasure9()!= null || meal.getStrMeasure9() == "") {
            mMeasures.append("\n : " + meal.getStrMeasure9());
        }
        if (meal.getStrMeasure10()!= null || meal.getStrMeasure10() == "") {
            mMeasures.append("\n : " + meal.getStrMeasure10());
        }
        if (meal.getStrMeasure11()!= null || meal.getStrMeasure11() == "") {
            mMeasures.append("\n : " + meal.getStrMeasure11());
        }
        if (meal.getStrMeasure12()!= null || meal.getStrMeasure12() == "") {
            mMeasures.append("\n : " + meal.getStrMeasure12());
        }
        if (meal.getStrMeasure13()!= null || meal.getStrMeasure13() == "") {
            mMeasures.append("\n : " + meal.getStrMeasure13());
        }
        if (meal.getStrMeasure14()!= null || meal.getStrMeasure14() == "") {
            mMeasures.append("\n : " + meal.getStrMeasure14());
        }
        if (meal.getStrMeasure15()!= null || meal.getStrMeasure15() == "") {
            mMeasures.append("\n : " + meal.getStrMeasure15());
        }
        if (meal.getStrMeasure16()!= null || meal.getStrMeasure16() == "") {
            mMeasures.append("\n : " + meal.getStrMeasure16());
        }
        if (meal.getStrMeasure17()!= null || meal.getStrMeasure17() == "") {
            mMeasures.append("\n : " + meal.getStrMeasure17());
        }
        if (meal.getStrMeasure18()!= null || meal.getStrMeasure18() == "") {
            mMeasures.append("\n : " + meal.getStrMeasure18());
        }
        if (meal.getStrMeasure19()!= null || meal.getStrMeasure19() == "") {
            mMeasures.append("\n : " + meal.getStrMeasure19());
        }
        if (meal.getStrMeasure20()!= null || meal.getStrMeasure20() == "") {
            mMeasures.append("\n : " + meal.getStrMeasure20());
        }


        mYoutube.setOnClickListener(v -> {
            Intent intentYoutube = new Intent(Intent.ACTION_VIEW);
            intentYoutube.setData(Uri.parse(meal.getStrYoutube()));
            startActivity(intentYoutube);
        });

        mSource.setOnClickListener(v -> {
            Intent intentSource = new Intent(Intent.ACTION_VIEW);
            intentSource.setData(Uri.parse(meal.getStrSource()));
            startActivity(intentSource);
        });

    }

    public void getMealById(String mealName){
        //Create Retrofit instance and parse the retrieved Json using Gson deserialiser
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").addConverterFactory(GsonConverterFactory.create()).build();
        //Get Service and call object for the request
        RecipeService service = retrofit.create(RecipeService.class);
        Call<MealLoreResponse> mealsCall = service.getMealByName(mealName);
        mealsCall.enqueue(new Callback<MealLoreResponse>() {
            @Override
            public void onResponse(Call<MealLoreResponse> call, Response<MealLoreResponse> response) {
                hideLoading();
                Log.d(TAG, "onSuccess: SUCCESS");
                setMeal(response.body().getMeals().get(0));
            }

            @Override
            public void onFailure(Call<MealLoreResponse> call, Throwable t) {
                hideLoading();
                Log.d(TAG, "onFailure: FAILURE");
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
