package com.example.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import static com.example.login.MainActivity.ID;

public class launchActivity extends AppCompatActivity {

    private static int time_out=3000;
    public static final String SHARED_PREF="sharedPrefs";
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
        i= sharedPreferences.getInt(ID,0);
        if(i==1||i==2||i==3)
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i1 = new Intent(launchActivity.this,Home.class);
                    startActivity(i1);
                    finish();
                }
            },time_out);
        }
        else
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(launchActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            },time_out);
            }
        }
    }

