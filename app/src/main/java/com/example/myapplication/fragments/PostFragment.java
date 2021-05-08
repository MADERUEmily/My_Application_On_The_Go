package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Post;

import com.example.myapplication.R;
import com.example.myapplication.adapters.PostAdapter;

import com.example.myapplication.models.PostAPI;
import com.example.myapplication.models.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PostFragment extends Fragment {
    //declare variables for all views
    View view;
    RecyclerView recyclerView;
    FloatingActionButton btnAdd;
    FirebaseAuth fAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    List<PostAPI> postList = new ArrayList<>();
    PostAdapter adapter;
    TextView tvMsg;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_post, container, false);

        //Initialize all the views
        recyclerView = view.findViewById(R.id.recycler_view);
        btnAdd = view.findViewById(R.id.fab_add);
        tvMsg = view.findViewById(R.id.tv_msg);
        progressBar = view.findViewById(R.id.progress_bar);

        //Initialize Firebase
        fAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("posts").child(fAuth.getUid());

        // getting all posts
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    tvMsg.setVisibility(View.GONE);
                    postList.clear();
                    Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();

                    for (DataSnapshot data : snapshotIterator) {
                        PostAPI post = data.getValue(PostAPI.class);
                        post.setKey(data.getKey());
                        postList.add(post);
                    }

                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
//                    load the posts in recyclerview
                    adapter = new PostAdapter(getActivity(), postList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
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
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                go to PostActivity for adding new post
                Intent intent = new Intent(getActivity(), Post.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
