////https://www.youtube.com/watch?v=18VcnYN5_LM  https://www.udemy.com/course/android-development-java-android-studio-masterclass/learn/lecture/14549914#questions
package com.example.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Post;
import com.example.myapplication.R;
import com.example.myapplication.ViewPostActivity;
import com.example.myapplication.models.PostAPI;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> {
    Context context;
    List<PostAPI> postList;

    public PostAdapter(Context context, List<PostAPI> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        Glide
                .with(context)
                .load(postList.get(position).getImageUrl())
                .centerCrop()
                .placeholder(R.drawable.icon)
                .into(holder.imageView);
        holder.textView.setText(postList.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewPostActivity.class);
                intent.putExtra("post", postList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        //TextView textView2;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            textView = itemView.findViewById(R.id.tv_title);
            // textView2 = itemView.findViewById(R.id.tv_content);
        }
    }
}
