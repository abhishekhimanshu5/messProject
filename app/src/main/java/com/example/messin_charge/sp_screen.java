package com.example.messin_charge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;

public class sp_screen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_screen);



       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent intent = new Intent(sp_screen.this,homeScreen.class);
               startActivity(intent);
               finish();

           }
       },1500);

    }
}