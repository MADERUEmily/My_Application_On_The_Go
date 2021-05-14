//https://www.youtube.com/watch?v=pVO1NVpPBF0&t=741s
package com.example.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Comment;
//import com.example.myapplication.utilities.TimeCalculator;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.Holder> {
    Context context;
    List<Comment> commentList;

    public CommentAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CommentAdapter.Holder holder, final int position) {
        String sourceString = "<b>" + commentList.get(position).getUserName() + "</b> " + commentList.get(position).getContent();
        holder.tvContent.setText(Html.fromHtml(sourceString));
        //holder.tvTime.setText(TimeCalculator.getTimeAgo(commentList.get(position).getTimeStamp()));
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tvContent, tvTime;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }
}
