package com.example.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;


public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    private Button back;
    private Button editions;
    private Button service;
    private Button privacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initView();
    }

    private void initView() {
        back = findViewById(R.id.back);
        editions = findViewById(R.id.edition);
        service = findViewById(R.id.service);
        privacy = findViewById(R.id.privacy);

        /**
         * 注册页面能点击的就三个地方
         * top处返回箭头、刷新验证码图片、注册按钮
         */
        back.setOnClickListener(this);
        editions.setOnClickListener(this);
        service.setOnClickListener(this);
        privacy.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.service:
                Intent intent2 = new Intent(this, ServiceActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.privacy:
                Intent intent3 = new Intent(this, PrivacyActivity.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.edition:
                Toast.makeText(this, "已是最新版本", Toast.LENGTH_SHORT).show();
        }
    }
}