package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.models.PostAPI;

public class ViewPostActivity extends AppCompatActivity {

    ImageView backgroundImageView;
    TextView currentUserTextView, postDateTextView, titleTextView, contentTextView;

    PostAPI postAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        postAPI = getIntent().getParcelableExtra("post");

        backgroundImageView = findViewById(R.id.background);
        currentUserTextView = findViewById(R.id.postUserNameTextView);
        postDateTextView = findViewById(R.id.postDateTextView);
        titleTextView = findViewById(R.id.title);
        contentTextView = findViewById(R.id.content);

        Glide
                .with(ViewPostActivity.this)
                .load(postAPI.getImageUrl())
                .centerCrop()
                .placeholder(R.drawable.icon)
                .into(backgroundImageView);

        currentUserTextView.setText(postAPI.getCreatorName());
        postDateTextView.setText(postAPI.getDate());
        titleTextView.setText(postAPI.getTitle());
        contentTextView.setText(postAPI.getContent());
    }
}