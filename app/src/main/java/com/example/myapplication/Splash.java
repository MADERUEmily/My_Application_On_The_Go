/////https://www.youtube.com/watch?v=-Y9fLnf0Tsc&list=PLFeNtfYFqlzmcg_cFO88rewsG5f0rs8RQ//
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent mainPage = new Intent(Splash.this, LogIn.class);
                    startActivity(mainPage);
                    finish();
                }
            }

        };
    }
}
