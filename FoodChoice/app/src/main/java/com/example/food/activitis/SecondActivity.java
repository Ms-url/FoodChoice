package com.example.food.activitis;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food.R;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    private String name;
    private int key;
    Button button_begin;
    TextView textView_place;
    TextView textView_food;
    private static String zhongxing_food[] = {"小面", "米线", "大众餐"};
    private static String shenshen_food[] = {"自选餐", "香香米", "大众餐", "面", "粉", "冒菜"};
    private static String yansheng_food[] = {"精品大众餐", "炒饭", "抄手", "面", "蛋包饭", "牛肉饭", "粉", "饺子", "冒菜", "烤肉饭", "锡纸饭", "盖饭"};

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    int po = (int) msg.obj;
                    if (key == 1) {
                        textView_food.setText(zhongxing_food[po]);
                    } else if (key == 2) {
                        textView_food.setText(yansheng_food[po]);
                    } else {
                        textView_food.setText(shenshen_food[po]);
                    }
                    break;
                case 2:
                    button_begin.setEnabled(true);

            }
        }
    };

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        button_begin = findViewById(R.id.button_begin);
        textView_food = findViewById(R.id.textView_food);
        textView_place = findViewById(R.id.textView_place);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //    SharedPreferences.Editor save_data = getSharedPreferences("user_data", MODE_PRIVATE).edit();

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        key = intent.getIntExtra("key", 9);

        textView_place.setText(name);
        if (key == 1) {
            textView_place.setBackgroundResource(R.drawable.rectangle_zhong);
            textView_place.setTextColor(textView_place.getResources().getColor(R.color.zhong));
        } else if (key == 2) {
            textView_place.setTextColor(textView_place.getResources().getColor(R.color.yan));
            textView_place.setBackgroundResource(R.drawable.rectangle_yan);
        } else {
            textView_place.setTextColor(textView_place.getResources().getColor(R.color.shen));
            textView_place.setBackgroundResource(R.drawable.rectangle_shen);
        }
        textView_food.setText("点击按钮开始");
        if (key == 0) {
            Toast.makeText(SecondActivity.this, "数据读取错误", Toast.LENGTH_SHORT).show();
        }

        button_begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = new Random().nextInt(12) + 37;
                int ik = new Random().nextInt(7);
                int ikk = new Random().nextInt(5);
                int ikkk = new Random().nextInt(19);
                int num;
                if (key == 1) {
                    num = 3;
                } else if (key == 2) {
                    num = 12;
                } else {
                    num = 6;
                }
                new Thread(() -> {
                    for (int n = 1; n <= i / num + i % num; n++) {
                        for (int ii = 0; ii < num; ii++) {
                            try {
                                Thread.sleep(n * 3);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            showResponse(1, ii);
                        }
                        showResponse(1, (i + ik + ikk + ikkk + 1) % num);
                    }
                    showResponse(2, 0);
                }).start();
            }
        });
    }
            private void showResponse(int num, int mas) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = num;
                        message.obj = mas;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        }