package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.recipeapp.Entities.Categories;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    //Declare variables for XML elements
    private String TAG = "CategoryActivity";
    private CategoryView view;
    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    private Toolbar mToolBar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private VpcAdapter mAdapter;

    //Initialise activity
    //Defines UI by binding activity_category and links widgets in UI to variables
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        //Link XML elements to their java variables
        //execute API function
        mTabLayout = findViewById(R.id.tabLayout);
        mToolBar = findViewById(R.id.toolbar);
        mViewPager = findViewById(R.id.viewPager);
        mAdapter = new VpcAdapter(getSupportFragmentManager(), new ArrayList<>());
        mViewPager.setAdapter(mAdapter);

        initActionBar();
        initIntent();
    }

    //Get activity title from intent
    //Set activity view by linking UI variables
    private void initIntent() {
        Intent intent = getIntent();
        List<Categories> categories = (List<Categories>) intent.getSerializableExtra(EXTRA_CATEGORY);
        int position = intent.getIntExtra(EXTRA_POSITION, 0);

        mAdapter = new VpcAdapter(getSupportFragmentManager(), categories);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(position, true);
        mAdapter.notifyDataSetChanged();
    }

    //Toolbar to set as the activity's action bar or null to clear it
    private void initActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    //When the user selects an item from the options menu, the system calls this method
    //Method passes the Menuitem selected
    //Identified by calling getItemId(), which returns the unique ID for the menu item
    //When successfully handled a menu ite, return true
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

}