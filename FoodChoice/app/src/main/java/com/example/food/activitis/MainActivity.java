package com.example.food.activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.food.R;

public class MainActivity extends AppCompatActivity {
    Button button_zhongxing;
    Button button_yansheng;
    Button button_shenshen;
    private static int ZHONG_XING = 1;
    private static int YAN_SHENG = 2;
    private static int SHEN_SHEN = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_shenshen = findViewById(R.id.button_shenshen);
        button_yansheng = findViewById(R.id.button_yansheng);
        button_zhongxing = findViewById(R.id.button_zhongxing);

        button_zhongxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("key", ZHONG_XING);
                intent.putExtra("name","中心食堂");
                startActivity(intent);
            }
        });
        button_yansheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("key", YAN_SHENG);
                intent.putExtra("name","延生食堂");
                startActivity(intent);
            }
        });
        button_shenshen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("key", SHEN_SHEN);
                intent.putExtra("name","莘莘食堂");
                startActivity(intent);
            }
        });

    }
}