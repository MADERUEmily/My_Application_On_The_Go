/////https://www.youtube.com/watch?v=fODp1hZxfng navigation
package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.fragments.MessageFragment;
import com.example.myapplication.fragments.PostFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    //declare variables for all views
    BottomNavigationView navigation;
    Button logoutBtn;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Initialize all the views
        navigation = (BottomNavigationView) findViewById(R.id.bottom_nv);
        logoutBtn = (Button) findViewById(R.id.button_sign_out);
        fAuth = FirebaseAuth.getInstance();

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.signOut();
                finish();
                startActivity(new Intent(Home.this, Log.class));
            }
        });
        // add setOnNavigationItemSelectedListener on selected items
        navigation.setOnNavigationItemSelectedListener(this);

        // load Home fragment
        loadFragment(new PostFragment());
    }



    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                // load Pantry fragment again after adding or editing the pantry item
                loadFragment(new PostFragment());
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.action_posts:

                // load Post fragment
                fragment = new PostFragment();
                loadFragment(fragment);
                return true;
            case R.id.action_messages:

                // load Message fragment
                fragment = new MessageFragment();
                loadFragment(fragment);
                return true;
        }
        return false;
    }


}