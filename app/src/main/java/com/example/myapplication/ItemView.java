//https://www.youtube.com/watch?v=h--MejqEfnM
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
//import com.example.myapplication.models.CartModel;
import com.example.myapplication.models.Item;
//import com.example.myapplication.utilities.CartCalculator;

public class ItemView extends AppCompatActivity {


    ImageView imageView;
    TextView tvName, tvPrice, tvDescription;
    Button btnAdd;
    Item item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);

        this.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        item = getIntent().getParcelableExtra("item");

        imageView = findViewById(R.id.img_view);
        tvName = findViewById(R.id.tv_name);
        tvPrice = findViewById(R.id.tv_price);
        tvDescription = findViewById(R.id.tv_description);
        btnAdd = findViewById(R.id.btn_add);

        Glide.with(ItemView.this).load(item.getItemImage()).into(imageView);
        tvName.setText(item.getItemName());
        tvPrice.setText("Euro" + item.getItemPrice());
        tvDescription.setText(item.getItemDescription());

      //  btnAdd.setOnClickListener(new View.OnClickListener() {
        //    @Override
          ///  public void onClick(View v) {
             //   CartModel model = new CartModel(1, item);
             //   CartCalculator.addToCart(model);
              //  Toast.makeText(ItemPreviewActivity.this, "Item add to cart", Toast.LENGTH_SHORT).show();
         //   }
       //});
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}