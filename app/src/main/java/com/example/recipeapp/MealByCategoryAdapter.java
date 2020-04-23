package com.example.recipeapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recipeapp.Entities.Meals;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MealByCategoryAdapter extends RecyclerView.Adapter<MealByCategoryAdapter.RecyclerViewHolder> {

    private List<Meals> mMeals;
    private Context context;
    private static ClickListener clickListener;

    public MealByCategoryAdapter(Context context, List<Meals> meals) {
        this.mMeals = meals;
        this.context = context;
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mThumb;
        public TextView mName;
        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mThumb = itemView.findViewById(R.id.mealThumb);
            mName = itemView.findViewById(R.id.mealName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public MealByCategoryAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.meal_by_category, viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealByCategoryAdapter.RecyclerViewHolder viewHolder, int i) {
        Meals meal = mMeals.get(i);
        Log.d(TAG, meal.getStrMealThumb());
        Glide.with(viewHolder.itemView).load(meal.getStrMealThumb()).fitCenter().into(viewHolder.mThumb);
        viewHolder.mName.setText(meal.getStrMeal());
    }


    @Override
    public int getItemCount() {
        return mMeals.size();
    }



    public void setOnItemClickListener(ClickListener clickListener) {
        MealByCategoryAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
