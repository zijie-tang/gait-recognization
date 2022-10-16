package com.example.Activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.app.ProgressDialog;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.service.PredictionTF;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class IdentifyActivity extends AppCompatActivity implements View.OnClickListener {
    private Button back;
    private ImageButton imageView;
    private ToggleButton cnn_lstm;
    private ToggleButton fourier;
    private ToggleButton wavelet;
    private ToggleButton IdNet;
    private ToggleButton dl_lstm;
    private ToggleButton cnn;
    private PredictionTF predictionTF;

    private static final String Model_file="files:///android_assets/frozen_model.pb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);

        back = findViewById(R.id.back);
        imageView = findViewById(R.id.imageView);
        cnn = findViewById(R.id.CNN);
        cnn_lstm = findViewById(R.id.cnn_lstm);
        fourier = findViewById(R.id.Fourier);
        wavelet = findViewById(R.id.Wavelet);
        IdNet = findViewById(R.id.IdNet);
        dl_lstm = findViewById(R.id.dl_lstm);

        /**
         * 注册页面能点击的就三个地方
         * top处返回箭头、刷新验证码图片、注册按钮
         */
        back.setOnClickListener(this);
        imageView.setOnClickListener(this);
        cnn.setOnClickListener(this);
        cnn_lstm.setOnClickListener(this);
        fourier.setOnClickListener(this);
        wavelet.setOnClickListener(this);
        IdNet.setOnClickListener(this);
        dl_lstm.setOnClickListener(this);
        //predictionTF=new PredictionTF(getAssets(), Model_file);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.imageView:
                ProgressDialog progressDialog = new ProgressDialog(IdentifyActivity.this);
                progressDialog.setTitle("身份验证");
                progressDialog.setMessage("鉴定中，请稍等……");
                progressDialog.setCancelable(true);
                progressDialog.show();

                delay2();

                break;

            case R.id.CNN:
                setEnable(cnn);
                break;
            case R.id.cnn_lstm:
                setEnable(cnn_lstm);
                break;
            case R.id.Fourier:
                setEnable(fourier);
                break;
            case R.id.Wavelet:
                setEnable(wavelet);
                break;
            case R.id.IdNet:
                setEnable(IdNet);
                break;
            case R.id.dl_lstm:
                setEnable(dl_lstm);
                break;

        }
    }
    private void setEnable(Button btn) {
        List<Button> buttonList=new ArrayList<>();
        if (buttonList.size()==0){
            buttonList.add(cnn);
            buttonList.add(cnn_lstm);
            buttonList.add(fourier);
            buttonList.add(wavelet);
            buttonList.add(IdNet);
            buttonList.add(dl_lstm);
        }
        for (int i = 0; i <buttonList.size() ; i++) {
            buttonList.get(i).setEnabled(true);
        }
        btn.setEnabled(false);
    }

    private void delay(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ProgressDialog progressDialog = new ProgressDialog(IdentifyActivity.this);
                progressDialog.setTitle("身份验证");
                progressDialog.setMessage("身份符合");
                progressDialog.setCancelable(true);
                progressDialog.show();
                delay2();
            }
        },2000);
    }
    private void delay2(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ProgressDialog progressDialog = new ProgressDialog(IdentifyActivity.this);
                progressDialog.setTitle("身份验证");
                progressDialog.setMessage("身份不符合，强制退出");
                progressDialog.setCancelable(true);
                progressDialog.show();
                Intent intent1 = new Intent(IdentifyActivity.this, LoginActivity.class);
                startActivity(intent1);
            }
        },4000);
    }
}
