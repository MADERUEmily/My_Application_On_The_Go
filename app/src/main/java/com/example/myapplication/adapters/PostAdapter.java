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
import com.example.myapplication.CommentActivity;
import com.example.myapplication.ViewPostActivity;
import com.example.myapplication.CommentActivity;
import com.example.myapplication.R;
import com.example.myapplication.ViewPostActivity;
import com.example.myapplication.models.PostAPI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

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
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {

        Glide
                .with(context)
                .load(postList.get(position).getImageUrl())
                .centerCrop()
                .placeholder(R.drawable.icon)
                .into(holder.imageView);

        holder.textView.setText(postList.get(position).getTitle());
//https://www.youtube.com/watch?v=U0snyuZWlyc add like functionality
        if (postList.get(position).isLike()) {
            holder.imgLike.setImageResource(R.drawable.ic_thumb_up_on);
        } else {
            holder.imgLike.setImageResource(R.drawable.ic_thumb_up_off);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewPostActivity.class);
                intent.putExtra("post", postList.get(position));
                context.startActivity(intent);
            }
        });
        //add comment ////https://www.youtube.com/watch?v=pVO1NVpPBF0&t=741s
        holder.imgComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CommentActivity.class);
                intent.putExtra("post", postList.get(position));
                context.startActivity(intent);
            }
        });

        holder.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance()
                        .getReference("posts")
                        .child(FirebaseAuth.getInstance().getUid())
                        .child(postList.get(position).getKey())
                        .child("like")
                        .setValue(!postList.get(position).isLike());
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView imageView, imgComment, imgLike;
        TextView textView;
        //TextView textView2;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            textView = itemView.findViewById(R.id.tv_title);
            imgComment = itemView.findViewById(R.id.img_comment);
            imgLike = itemView.findViewById(R.id.img_like);
            // textView2 = itemView.findViewById(R.id.tv_content);
        }
    }
}
