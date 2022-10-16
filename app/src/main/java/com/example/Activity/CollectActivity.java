package com.example.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.example.Bean.ACC_info;
import com.example.myapplication.R;
import com.example.service.CollectService;
import com.example.service.UserService;
import java.util.ArrayList;
import java.util.List;

public class CollectActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener {

    private SensorManager mSensorMgr;
    private Button back;
    private TextView tvx;
    private TextView tvy;
    private TextView tvz;
    static public List<String> LS;
    static public List<Float> XAxis;
    static public List<Float> YAxis;
    static public List<Float> ZAxis;
    private int SENSOR_RATE_NORMAL=20000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);

        LS = new ArrayList<String>();
        XAxis=new ArrayList<Float>();
        YAxis=new ArrayList<Float>();
        ZAxis=new ArrayList<Float>();

        Button bt = findViewById(R.id.bt_dsp);
        bt.setOnClickListener(this);

        Button bt_stop = findViewById(R.id.bt_stop);
        bt_stop.setOnClickListener(this);

        back=findViewById(R.id.back);
        back.setOnClickListener(this);

        tvx = findViewById(R.id.tvx);
        tvy = findViewById(R.id.tvy);
        tvz = findViewById(R.id.tvz);
        //获取系统的传感器管理服务
        mSensorMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    protected void onPause() {//暂停传感器的捕获
        super.onPause();
        mSensorMgr.unregisterListener(this);
    }

    protected void onResume() {//为系统的加速度传感器注册监听器
        super.onResume();
    }

    protected void onStop() {//取消注册
        super.onStop();
        mSensorMgr.unregisterListener(this);

    }

    public void onSensorChanged(SensorEvent event) {// 当传感器的值发生改变时回调该方法
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float[] values = event.values;
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = formatter.format(date);
            String s = "";
            long timeCurrentTimeMillis = System.currentTimeMillis();
            s = timeCurrentTimeMillis + " " + Float.toString(values[0]) + " " + Float.toString(values[1]) + " " + Float.toString(values[2]) + "\n";
            LS.add(s);
            XAxis.add(values[0]);
            YAxis.add(values[1]);
            ZAxis.add(values[2]);
            if(timeCurrentTimeMillis%100<=10||timeCurrentTimeMillis%100>=90) {
                tvx.setText("ACC_X: " + Float.toString(values[0]));
                tvy.setText("ACC_Y: " + Float.toString(values[1]));
                tvz.setText("ACC_Z: " + Float.toString(values[2]));
            }
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {//不用处理，空着就行
        return;
    }

    private static final String TAG = "ACCCollection:";

    public void writeLS(List<String> LS) {
        FileOutputStream fos;
        CollectService collectService=new CollectService((CollectActivity.this));
        UserService userService=new UserService(CollectActivity.this);
        int userId=userService.search(LoginActivity.username,LoginActivity.password);
        try{
            fos=openFileOutput("ACCCollection.txt",MODE_APPEND);
            for (int i = 0; i < LS.size(); i++) {
                String text=LS.get(i)+"\n";
                fos.write(text.getBytes());
                String info[]=LS.get(i).split(" ");
                ACC_info acc_info=new ACC_info(info[0],info[1],info[2],info[3],userId);
                collectService.add(acc_info);
                if(i>=256){
                    break;
                }
            }
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void delay(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent1 = new Intent(CollectActivity.this, DisplayActivity.class);
                startActivity(intent1);
                finish();
            }
        },2000);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.back:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.bt_dsp:
                mSensorMgr.unregisterListener(this, mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
                mSensorMgr.registerListener(this, mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SENSOR_RATE_NORMAL);
                break;
            case R.id.bt_stop:
                ProgressDialog progressDialog = new ProgressDialog(CollectActivity.this);
                progressDialog.setTitle("数据采集");
                progressDialog.setMessage("数据存储中，请稍等……");
                progressDialog.setCancelable(true);
                progressDialog.show();
                delay();
                mSensorMgr.unregisterListener(this);
                writeLS(LS);
                Toast.makeText(this, "数据采集成功，共采集" + LS.size() + "条数据", Toast.LENGTH_SHORT).show();
        }
    }
}
