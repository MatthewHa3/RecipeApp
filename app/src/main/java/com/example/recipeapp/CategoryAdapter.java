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


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.RecyclerViewHolder> {

    private List<Categories> mCategories;
    private Context context;
    private static ClickListener clickListener;

    public CategoryAdapter(List<Categories> categories, Context context) {
        this.mCategories = categories;
        this.context = context;
    }

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

    @NonNull
    @Override
    public CategoryAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.category, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.RecyclerViewHolder viewHolder, int i) {
        Categories category = mCategories.get(i);
        Glide.with(viewHolder.itemView).load(category.getStrCategoryThumb()).fitCenter().into(viewHolder.mThumb);
        viewHolder.mName.setText(category.getStrCategory());
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        CategoryAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }

    public void setCategories(List<Categories> categories){
        for (Categories category : categories){
            Log.w("CategoryAdapter:", category.getStrCategory());
        }
        mCategories.addAll(categories);
        notifyDataSetChanged();
    }
}
