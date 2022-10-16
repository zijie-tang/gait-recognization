package com.example.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    private Button back;
    private Button account;
    private Button mode;
    private Button privilege;
    private Button function;
    private Button logout;
    private Button manage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
    }

    private void initView() {
        back = findViewById(R.id.back);
        account = findViewById(R.id.account);
        mode = findViewById(R.id.mode);
        privilege = findViewById(R.id.privilege);
        function = findViewById(R.id.function);
        logout = findViewById(R.id.logout);
        manage = findViewById(R.id.manage);

        /**
         * 注册页面能点击的就三个地方
         * top处返回箭头、刷新验证码图片、注册按钮
         */
        back.setOnClickListener(this);
        account.setOnClickListener(this);
        mode.setOnClickListener(this);
        privilege.setOnClickListener(this);
        function.setOnClickListener(this);
        logout.setOnClickListener(this);
        manage.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.logout:
                Toast.makeText(this, "退出当前账号", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this, LoginActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.account:
                Intent intent3 = new Intent(this, AccountActivity.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.mode:
                Intent intent4 = new Intent(this, ModeActivity.class);
                startActivity(intent4);
                finish();
                break;
            case R.id.manage:
                Intent intent5 = new Intent(this, ManageActivity.class);
                startActivity(intent5);
                finish();
                break;
            case R.id.function:
                Intent intent6 = new Intent(this, FunctionActivity.class);
                startActivity(intent6);
                finish();
                break;
            case R.id.privilege:
                Intent intent7 = new Intent(this, PrivilegeActivity.class);
                startActivity(intent7);
                finish();
                break;
        }
    }
}
