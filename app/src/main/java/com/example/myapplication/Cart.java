////https://www.youtube.com/watch?v=18VcnYN5_LM  ///https://www.youtube.com/watch?v=18VcnYN5_LM&t=662s //https://www.youtube.com/watch?v=5T144CbTwjc&list=PLk7v1Z2rk4hjHrGKo9GqOtLs1e2bglHHA
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplication.adapters.CategoryAdapter;
import com.example.myapplication.models.Category;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;
    List<Category> categoryList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        addDataInCategoryList();
//add recyclerview to show the items
        recyclerView = findViewById(R.id.recycler_view);

        categoryAdapter = new CategoryAdapter(Cart.this,categoryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(Cart.this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(categoryAdapter);
    }
//add categories's name
    public void addDataInCategoryList(){
        categoryList.add(new Category(1,"Arnott's - Make Up"));
        categoryList.add(new Category(2,"Boots - Medicines"));
        categoryList.add(new Category(3,"Penny's - Women Fashion"));


    }
}
