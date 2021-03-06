////https://www.youtube.com/watch?v=18VcnYN5_LM  ///https://www.youtube.com/watch?v=18VcnYN5_LM&t=662s //https://www.youtube.com/watch?v=5T144CbTwjc&list=PLk7v1Z2rk4hjHrGKo9GqOtLs1e2bglHHA
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplication.adapters.CategoryAdapter;
import com.example.myapplication.adapters.ItemAdapter;
import com.example.myapplication.models.Category;
import com.example.myapplication.models.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ItemAdapter itemAdapter;

    List<Item> itemList = new ArrayList<>();
    int categoryId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        addDataInItemList();

        categoryId = getIntent().getIntExtra("categoryId", 1);

        recyclerView = findViewById(R.id.recycler_view);



        itemAdapter = new ItemAdapter(ItemActivity.this,getDataUsingCategoryId(categoryId) );
        recyclerView.setLayoutManager(new GridLayoutManager(ItemActivity.this, 2));
//        recyclerView.setLayoutManager(new LinearLayoutManager(ItemActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(itemAdapter);
    }
//add items info to the recyclerview
    public void addDataInItemList() {
        itemList.add(new Item(1, 1, R.drawable.lipstick2, 10, "Top Lipstick - shape 001", "The lipstick is amazing, the shade is 001."));
        itemList.add(new Item(2, 1, R.drawable.lipstick3, 14, "Top Lipstick - shape 002", "The lipstick is amazing, the shade is 002"));
        itemList.add(new Item(3, 1, R.drawable.lipstick4, 8, "Top Lipstick - shape 003", "The lipstick is amazing, the shade is 003"));
        itemList.add(new Item(4, 1, R.drawable.lipstick5, 10, "Top Lipstick - shape 004", "The lipstick is amazing, the shade is 004"));
        itemList.add(new Item(5, 1, R.drawable.lipstick6, 8, "Top Lipstick - shape 005", "The lipstick is amazing, the shade is 005"));
        itemList.add(new Item(6, 1, R.drawable.lipstick7, 18, "Top Lipstick - shape 006", "The lipstick is amazing, the shade is 006"));
        itemList.add(new Item(7, 1, R.drawable.lipstick8, 14, "Top Lipstick - shape 007", "The lipstick is amazing, the shade is 007"));
        itemList.add(new Item(8, 1, R.drawable.lipstick9, 6, "Top Lipstick - shape 008", "The lipstick is amazing, the shade is 008"));
        itemList.add(new Item(9, 1, R.drawable.lipstick10, 13, "Top Lipstick - shape 009", "The lipstick is amazing, the shade is 009"));
        itemList.add(new Item(10, 1, R.drawable.lipstick11, 13, "Top Lipstick - shape 010", "The lipstick is amazing, the shade is 010"));
        itemList.add(new Item(11, 1, R.drawable.lipstick12, 19, "Top Lipstick - shape 011", "The lipstick is amazing, the shade is 011"));
        itemList.add(new Item(12, 1, R.drawable.lipstick13, 9, "Top Lipstick - shape 012", "The lipstick is amazing, the shade is 012"));
        itemList.add(new Item(13, 1, R.drawable.lipstick14, 28, "Top Lipstick - shape 013", "The lipstick is amazing, the shade is 013"));
        itemList.add(new Item(14, 1, R.drawable.lipstick15, 14, "Top Lipstick - shape 014", "The lipstick is amazing, the shade is 014"));
        itemList.add(new Item(15, 2, R.drawable.eyedrop, 15, "Top eye drop", "This item is super good for your eye"));
        itemList.add(new Item(16, 2, R.drawable.pain1, 7, "Top Pain Killer", "This item is super good for your body pain"));
        itemList.add(new Item(17, 2, R.drawable.vitamin, 13, "Top Vitamin", "This item is super good for your health"));
        itemList.add(new Item(18, 2, R.drawable.hair, 14, "Top Hair growth", "This item is super good for your hair growth"));
        itemList.add(new Item(19, 2, R.drawable.sleep, 10, "Top sleep Treatment", "This item is super good for your sleep"));
        itemList.add(new Item(20, 2, R.drawable.flu, 9, "Top Flu pill", "This item is super good for your flu"));
        itemList.add(new Item(21, 2, R.drawable.stomach, 8, "Top stomach relief", "This item is super good for your stomach"));
        itemList.add(new Item(22, 2, R.drawable.immune, 13, "Top immune tablets", "This item is super good for your immune system"));
        itemList.add(new Item(23, 2, R.drawable.allergy, 10, "Top allergy treatment", "This item is super good for your allergy condition"));
        itemList.add(new Item(24, 3, R.drawable.dress, 16, "Top Dress", "This is top sell dress"));
        itemList.add(new Item(25, 3, R.drawable.wallet, 11, "Top Wallet", "This is top sell Wallet"));
        itemList.add(new Item(26, 3, R.drawable.glass, 10, "Top Glass", "This is top sell pair of Glass"));
        itemList.add(new Item(27, 3, R.drawable.shoes, 4, "Top Shoes", "This is top sell Shoes"));
        itemList.add(new Item(28, 3, R.drawable.jeans, 5, "Top Jeans", "This is top sell Jeans"));
//collect from the adapter https://www.youtube.com/watch?v=ZqQuYgwrtAI show list of objects in the the user UI
        Collections.shuffle(itemList);
    }


    public List<Item> getDataUsingCategoryId(int categoryId) {
        List<Item> newItemList = new ArrayList<>();
        for (Item item : itemList) {
            if (item.getCategoryId() == categoryId)
                newItemList.add(item);
        }
        return newItemList;
    }
  //  @Override
 //   public boolean onCreateOptionsMenu(Menu menu) {
   //     getMenuInflater().inflate(R.menu.menu_cart, menu);
    //    return true;
 //   }

   // @Override
  // public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
     //   if (item.getItemId() == R.id.action_cart) {
           // Intent intent = new Intent(ItemActivity.this, ItemView.class);
        //    startActivity(intent);
         //   return true;
        //}
       // return super.onOptionsItemSelected(item);
   // }
}