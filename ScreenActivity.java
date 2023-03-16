package com.example.BugWiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        Thread thread = new Thread(){
            @Override
            public void run(){
                try{
                    sleep(3000);
                    startActivity(new Intent(ScreenActivity.this, HomeActivity.class));
                    finish();
                }catch(Exception e){

                }
            }
        }; thread.start();
    }
}