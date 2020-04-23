package com.example.recipeapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.recipeapp.Entities.Categories;

import java.util.List;

//Implementation of PagerAdapter that requires each page as a Fragment that is kept in the fragment manager
public class VpcAdapter extends FragmentPagerAdapter {

    private List<Categories> categories;

    public VpcAdapter(FragmentManager fm, List<Categories> categories) {
        super(fm);
        this.categories = categories;
    }

    //Return the Fragment associated with a specified positi ive coon
    @Override
    public Fragment getItem(int i) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString("EXTRA_DATA_NAME", categories.get(i).getStrCategory());
        args.putString("EXTRA_DATA_DESC", categories.get(i).getStrCategoryDescription());
        args.putString("EXTRA_DATA_IMG", categories.get(i).getStrCategoryThumb());
        fragment.setArguments(args);
        return fragment;
    }

    // Return the size of meal data set so that it knows how many list item views it will need to recycle
    @Override
    public int getCount() {
        return categories.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return categories.get(position).getStrCategory();
    }
}