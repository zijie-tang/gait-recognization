package com.example.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button identification;
    private Button collection;
    private Button setting;
    private Button feedback;
    private Button brochure;
    private Button about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitView();
    }

    private void InitView(){
        identification=findViewById(R.id.identification);
        collection=findViewById(R.id.collection);
        setting=findViewById(R.id.setting);
        feedback=findViewById(R.id.feedback);
        brochure=findViewById(R.id.brochure);
        about=findViewById(R.id.about);

        identification.setOnClickListener(this);
        collection.setOnClickListener(this);
        setting.setOnClickListener(this);
        feedback.setOnClickListener(this);
        brochure.setOnClickListener(this);
        about.setOnClickListener(this);
    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.identification:
                startActivity(new Intent(MainActivity.this, IdentifyActivity.class));
                finish();
                break;
            case R.id.collection:
                startActivity(new Intent(MainActivity.this, CollectActivity.class));
                finish();
                break;
            case R.id.brochure:
                startActivity(new Intent(MainActivity.this, BrochureActivity.class));
                finish();
                break;
            case R.id.feedback:
                startActivity(new Intent(MainActivity.this, FeedbackActivity.class));
                finish();
                break;
            case R.id.about:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                finish();
                break;
            case R.id.setting:
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                finish();
        }
    }
}