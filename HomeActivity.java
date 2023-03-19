package com.example.BugWiki;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

// 민정님이 보내주신 코드(위의 import로 해결)
//import android.support.v7.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 메인페이지-이미지 전환 위해 추가한 부분
        Button imageButton = (Button) findViewById(R.id.button);
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        ///////////이부분부터//////////
        // 메인페이지-내주변 방역업체 페이지 연결
        ImageButton mapButton = (ImageButton) findViewById(R.id.button4);
        mapButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });
        ///////////여기까지 추가됨//////////

        // 민정님-코틀린 12.18 새로추가
        ImageButton questionButton = (ImageButton) findViewById(R.id.button3);
        questionButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),QuestionActivity.class);
                startActivity(intent);
            }
        });

        // 커뮤니티 위해 새로 추가(0312)
//        Button communityButton = (Button) findViewById(R.id.button2);
//        communityButton.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view){
//                Intent intent = new Intent(getApplicationContext(), QnaActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}