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

//Creating adapter to connect data with View items. Adapter uses ViewHOlder that describes a View item and its position
//implements onClickListener
public class MealByCategoryAdapter extends RecyclerView.Adapter<MealByCategoryAdapter.RecyclerViewHolder> {

    private List<Meals> mMeals;
    private Context context;
    private static ClickListener clickListener;

    //Custom adapter for Meals
    public MealByCategoryAdapter(Context context, List<Meals> meals) {
        this.mMeals = meals;
        this.context = context;
    }

    //A vieweholder describes an item view and metadata about its place within the Recyclerview
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

    //method inflates thh item layout, returns a ViewHolder with the layout and the adapter
    @NonNull
    @Override
    public MealByCategoryAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.meal_by_category, viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    // Method connects meal data to the view holder
    // Called by RecyclerView to display data at the specified position
    @Override
    public void onBindViewHolder(@NonNull MealByCategoryAdapter.RecyclerViewHolder viewHolder, int i) {
        Meals meal = mMeals.get(i);
        Glide.with(viewHolder.itemView).load(meal.getStrMealThumb()).fitCenter().into(viewHolder.mThumb);
        viewHolder.mName.setText(meal.getStrMeal());
    }


    // Return the size of meal data set so that it knows how many list item views it will need to recycle
    @Override
    public int getItemCount() {
        return mMeals.size();
    }


    //Callback method to be invoked when an item in this AdapterView has been clicked
    public void setOnItemClickListener(ClickListener clickListener) {
        MealByCategoryAdapter.clickListener = clickListener;
    }

    //Interface for a callback to be invoked when a view is clicked
    public interface ClickListener {
        void onClick(View view, int position);
    }
}