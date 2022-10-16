package com.example.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class PrivilegeActivity extends AppCompatActivity implements View.OnClickListener{

    private Button back;
    private Button location;
    private Button camera;
    private Button photo;
    private Button microphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privilege);

        initView();
    }

    private void initView() {
        back = findViewById(R.id.back);
        location = findViewById(R.id.location);
        camera = findViewById(R.id.camera);
        photo = findViewById(R.id.photo);
        microphone = findViewById(R.id.microphone);

        /**
         * 注册页面能点击的就三个地方
         * top处返回箭头、刷新验证码图片、注册按钮
         */
        back.setOnClickListener(this);
        camera.setOnClickListener(this);
        photo.setOnClickListener(this);
        microphone.setOnClickListener(this);
        location.setOnClickListener(this);
    }

    public void onClick(View view) {
        if(view.getId()==R.id.back){
            Intent intent1 = new Intent(this, SettingActivity.class);
            startActivity(intent1);
            finish();
        }else {
            Uri packageURI = Uri.parse("package:" + "com.example.myapplication");
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
            startActivity(intent);

        }
    }
}
