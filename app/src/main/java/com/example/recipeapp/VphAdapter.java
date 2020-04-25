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

//Implement a pagerAdapter that represents each page as a fragment
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

    // Return the size of meal data set so that it knows how many list item views it will need to recycle
    @Override
    public int getCount() {
        return mMeals.size();
    }

    //This method is required for a PagerAdapter to function properly
    //Page view to check for association with object, object to check for association with view
    //true if view is associated with te key object o
    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view.equals(o);
    }

    //Create the page for the given position
    //Adapter is responsible for adding the view to the container given here
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

    //Remove a page from the given position
    //Adapter is responsible for removing the view from its container
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    //Interface for a callback to be invoked when a view is clicked
    public interface ClickListener {
        void onClick(View v, int position);
    }

    //Notifies that the data has been changed and any View reflecting the data set should refresh
    public void setMeals(List<Meals> meals){
        mMeals.addAll(meals);
        notifyDataSetChanged();
    }
}