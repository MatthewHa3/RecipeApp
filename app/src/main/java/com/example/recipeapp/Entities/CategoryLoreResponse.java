package com.example.recipeapp.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryLoreResponse implements Serializable {

    @SerializedName("categories")
    @Expose
    private List<Categories> categories = new ArrayList<Categories>();

    public CategoryLoreResponse(){
    }

    public CategoryLoreResponse(List<Categories> categories){
        super();
        this.categories = categories;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

}
