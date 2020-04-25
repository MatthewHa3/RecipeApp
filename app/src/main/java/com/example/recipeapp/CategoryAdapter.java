package com.example.recipeapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.recipeapp.Entities.Categories;
import java.util.List;

//Creating adapter to connect data with View items. Adapter uses ViewHOlder that describes a View item and its position
//implements onClickListener
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.RecyclerViewHolder> {

    private List<Categories> mCategories;
    private Context context;
    private static ClickListener clickListener;

    //Custom adapter for Category List
    public CategoryAdapter(List<Categories> categories, Context context) {
        this.mCategories = categories;
        this.context = context;
    }

    // Return the size of category data set so that it knows how many list item views it will need to recycle
    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mThumb;
        public TextView mName;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mThumb = itemView.findViewById(R.id.categoryThumb);
            mName = itemView.findViewById(R.id.categoryName);
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
    public CategoryAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.category, parent, false);
        return new RecyclerViewHolder(view);
    }

    // Method connects category data to the view holder
    // Called by RecyclerView to display data at the specified position
    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.RecyclerViewHolder viewHolder, int i) {
        Categories category = mCategories.get(i);
        Glide.with(viewHolder.itemView).load(category.getStrCategoryThumb()).fitCenter().into(viewHolder.mThumb);
        viewHolder.mName.setText(category.getStrCategory());
    }

    //Callback method to be invoked when an item in this AdapterView has been clicked
    public void setOnItemClickListener(ClickListener clickListener) {
        CategoryAdapter.clickListener = clickListener;
    }

    //Interface for a callback to be invoked when a view is clicked
    public interface ClickListener {
        void onClick(View view, int position);
    }

    public void setCategories(List<Categories> categories){
        mCategories.addAll(categories);
        notifyDataSetChanged();
    }
}