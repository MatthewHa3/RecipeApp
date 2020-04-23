package com.example.recipeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import com.example.recipeapp.Entities.Meals;
import com.squareup.picasso.Picasso;
import java.util.List;

public class VphAdapter extends PagerAdapter {

    private List<Meals> mMeals;
    private Context context;
    private static ClickListener clickListener;

    public VphAdapter(List<Meals> meals, Context context) {
        this.mMeals = meals;
        this.context = context;
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        VphAdapter.clickListener = clickListener;
    }

    @Override
    public int getCount() {
        return mMeals.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view.equals(o);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.view_pager_header,
                container,
                false
        );


        ImageView mealThumb = view.findViewById(R.id.mealThumb);
        TextView mealName = view.findViewById(R.id.mealName);

        String strMealThumb = mMeals.get(position).getStrMealThumb();
        Picasso.get().load(strMealThumb).into(mealThumb);

        String strMealName = mMeals.get(position).getStrMeal();
        mealName.setText(strMealName);

        view.setOnClickListener(v -> clickListener.onClick(v, position));
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    public interface ClickListener {
        void onClick(View v, int position);
    }

    public void setMeals(List<Meals> meals){
        mMeals.addAll(meals);
        notifyDataSetChanged();
    }
}