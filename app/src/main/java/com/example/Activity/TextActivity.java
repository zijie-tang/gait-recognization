package com.example.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class TextActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private Button back;
    private TextView tv1;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        back=findViewById(R.id.back);
        seekBar=findViewById(R.id.seekBar);
        tv1=findViewById(R.id.text);
        //设置监听器 监听数值改变情况
        seekBar.setOnSeekBarChangeListener(this);
        back.setOnClickListener(this);
        }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        tv1.setText("      拖动下面滑块，可以设置字体大小。\n      设置后可改变主体部分的字体大小。如果在使用过程中存在问题或意见，可以反馈给步态身份识别团队。");

        tv1.setTextSize(22+(progress-50)/5);
    }
    //开始拖动
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }
    //停止拖动
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                Intent intent1 = new Intent(this, FunctionActivity.class);
                startActivity(intent1);
                finish();
        }
    }
}
