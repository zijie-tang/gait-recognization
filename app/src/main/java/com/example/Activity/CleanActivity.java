package com.example.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.service.DataCleanManager;

public class CleanActivity  extends AppCompatActivity implements View.OnClickListener {

    private Button back;
    private TextView mMtvGetCache;
    private Button mBtnClearCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean);

        initView();
    }

    private void initView() {
        back = findViewById(R.id.back);
        back.setOnClickListener(this);

        mMtvGetCache = (TextView) findViewById(R.id.mtv_getCache);
        mMtvGetCache.setOnClickListener(this);
        mBtnClearCache = (Button) findViewById(R.id.clean);
        mBtnClearCache.setOnClickListener(this);


        try {
            String cacheSize = DataCleanManager.getTotalCacheSize(this);
            mMtvGetCache.setText(cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                Intent intent1 = new Intent(this, FunctionActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.clean:
                try {
                    DataCleanManager.cleanInternalCache(CleanActivity.this);
                    String cacheSize2 = "0.00B";
                    mMtvGetCache.setText(cacheSize2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }