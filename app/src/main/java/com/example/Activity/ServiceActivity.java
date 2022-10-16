package com.example.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener{
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        initView();
    }

    private void initView() {
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    public void onClick(View view) {
        if(view.getId()==R.id.back){
            Intent intent1 = new Intent(this, AboutActivity.class);
            startActivity(intent1);
            finish();
        }
    }
}
