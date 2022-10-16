package com.example.Activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.myapplication.R;

public class FunctionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button back;
    private Button textSize;
    private Button clean;
    private Switch IconSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);

        initView();
    }

    private void initView() {
        back = findViewById(R.id.back);
        clean = findViewById(R.id.Clean);
        textSize = findViewById(R.id.TextSize);
        IconSwitch = findViewById(R.id.IconSwitch);
        //设置按钮点击监听器
        back.setOnClickListener(this);
        clean.setOnClickListener(this);
        textSize.setOnClickListener(this);
        IconSwitch.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.back:
                Intent intent1 = new Intent(this, SettingActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.TextSize:
                Intent intent2=new Intent(this,TextActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.Clean:
                Intent intent3=new Intent(this,CleanActivity.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.IconSwitch:
                String str="";
                if(IconSwitch.isChecked()==true){
                    str="图标显示关闭";
                }else{
                    str="图标显示开启";
                }
                Toast.makeText(FunctionActivity.this, str , Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
