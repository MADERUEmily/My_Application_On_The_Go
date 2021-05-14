//https://www.youtube.com/watch?v=pVO1NVpPBF0&t=741s
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapters.CommentAdapter;
import com.example.myapplication.models.Comment;
import com.example.myapplication.models.PostAPI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {
    EditText etComment;
    ImageView imgSend;
    RecyclerView recyclerView;
    FirebaseAuth fAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    List<Comment> commentList = new ArrayList<>();
    CommentAdapter adapter;
    TextView tvMsg;
    ProgressBar progressBar;

    PostAPI postAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        this.setTitle("Comments");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        postAPI = getIntent().getParcelableExtra("post");

        recyclerView = findViewById(R.id.recycler_view);
        etComment = findViewById(R.id.et_comment);
        imgSend = findViewById(R.id.img_send);
        tvMsg = findViewById(R.id.tv_msg);
        progressBar = findViewById(R.id.progress_bar);

        //Initialize Firebase
        fAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("comments").child(postAPI.getKey());

        // getting all posts
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    tvMsg.setVisibility(View.GONE);
                    commentList.clear();
                    Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();

                    for (DataSnapshot data : snapshotIterator) {
                        Comment comment = data.getValue(Comment.class);
                        comment.setKey(data.getKey());
                        commentList.add(comment);
                    }

                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
//                    load the posts in recyclerview
                    adapter = new CommentAdapter(CommentActivity.this, commentList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(CommentActivity.this, LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(adapter);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    tvMsg.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(CommentActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = etComment.getText().toString();
                Comment comment = new Comment(content, fAuth.getUid(), postAPI.getCreatorName());
                myRef.push().setValue(comment);
                etComment.setText(null);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
