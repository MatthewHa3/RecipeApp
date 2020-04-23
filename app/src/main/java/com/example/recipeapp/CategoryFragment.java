package com.example.recipeapp;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class CategoryFragment extends Fragment implements CategoryView {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private CategoryView view;
    private MealByCategoryAdapter mAdapter = new MealByCategoryAdapter(getActivity(), new ArrayList<>());

    /*@Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            getMealByCategory(getArguments().getString("EXTRA_DATA_NAME"));
        }
    }*/
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        mProgressBar = view.findViewById(R.id.progressBar);
        mRecyclerView = view.findViewById(R.id.rvMealByC);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

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

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

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
            Toast.makeText(getActivity(), "meal: " + meals.get(position).getStrMeal(), Toast.LENGTH_SHORT).show();
        }));
    }




}