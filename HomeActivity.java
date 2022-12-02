package com.example.bug_1128;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// 민정님이 보내주신 코드(위의 import로 해결)
//import android.support.v7.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}