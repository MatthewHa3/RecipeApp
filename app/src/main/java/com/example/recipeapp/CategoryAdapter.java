package com.example.recipeapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipeapp.Entities.Categories;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.RecyclerViewHolder> {

    private List<Categories> mCategories;
    private Context context;
    private static ClickListener clickListener;

    public CategoryAdapter(List<Categories> categories, Context context) {
        this.mCategories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.category,
                viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.RecyclerViewHolder viewHolder, int i) {

        String strCategoryThum = mCategories.get(i).getStrCategoryThumb();
        Picasso.get().load(strCategoryThum).placeholder(R.drawable.ic_launcher_foreground).into(viewHolder.mThumb);

        String strCategoryName = mCategories.get(i).getStrCategory();
        viewHolder.mName.setText(strCategoryName);
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
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        CategoryAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }

    public void setCategories(List<Categories> categories){
        mCategories.clear();
        mCategories.addAll(categories);
        notifyDataSetChanged();
    }
}
