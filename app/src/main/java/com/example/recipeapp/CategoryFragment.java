package com.example.recipeapp;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.recipeapp.Entities.MealLoreResponse;
import com.example.recipeapp.Entities.Meals;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment implements CategoryView {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private MealByCategoryAdapter mAdapter = new MealByCategoryAdapter(getActivity(), new ArrayList<>());

    //Have fragment instantiated to its user interface view
    //LayoutInflater used to inflate any views in fragment
    //ViewGroup if not null is the parent view that the fragment's UI is attached to
    //SavedInstance if not null is reconstructed from a previous saved state
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        mProgressBar = view.findViewById(R.id.progressBar);
        mRecyclerView = view.findViewById(R.id.rvMealByC);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    //Called after onCreate has returned but before an saved state has been restored in to the view
    //The fragment's view is not attached to tis parent
    //SavedInstance if not null is reconstructed from a previous state. This may be Null
    //Glide is an image loader library to display the category images
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null){
            ((TextView) view.findViewById(R.id.textCategory)).setText(getArguments().getString("EXTRA_DATA_DESC"));
            Glide.with(view.findViewById(R.id.imageCategory)).load(getArguments().getString("EXTRA_DATA_IMG")).fitCenter().into((ImageView)view.findViewById(R.id.imageCategory));
            Glide.with(view.findViewById(R.id.imageCategoryBg)).load(getArguments().getString("EXTRA_DATA_IMG")).fitCenter().into((ImageView)view.findViewById(R.id.imageCategoryBg));
            CategoryPresenter presenter = new CategoryPresenter(this);
            presenter.getMealByCategory(getArguments().getString("EXTRA_DATA_NAME"));
        }
    }


    //If view not loaded, show progress bar
    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    //Ends progress bar by making it invisible
    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setMeals(List<Meals> meals) {
        mAdapter = new MealByCategoryAdapter(getActivity(), meals);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setClipToPadding(false);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mAdapter.setOnItemClickListener(((view, position) -> {
            TextView mealName = view.findViewById(R.id.mealName);
            Intent intent = new Intent(getActivity(), RecipeActivity.class);
            intent.putExtra(RecipeActivity.EXTRA_RECIPE, mealName.getText().toString());
            startActivity(intent);
        }));
    }

}
