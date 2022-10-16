package com.example.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView back;
    private Button main;
    private Button login;
    private TextView account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        initView();
        account.setText("用户名："+LoginActivity.username);
    }

    private void initView() {
        back = findViewById(R.id.back);
        main = findViewById(R.id.backToMain);
        login = findViewById(R.id.backToLogin);
        account = findViewById(R.id.account);
        /**
         * 注册页面能点击的就三个地方
         * top处返回箭头、刷新验证码图片、注册按钮
         */
        main.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent intent1 = new Intent(this, SettingActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.backToLogin:
                Toast.makeText(this, "退出当前账号", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this, LoginActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.backToMain:
                Intent intent3 = new Intent(this, MainActivity.class);
                startActivity(intent3);
                finish();
                break;
        }
    }
}
