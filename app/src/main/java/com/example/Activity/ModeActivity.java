package com.example.Activity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.myapplication.R;
import androidx.appcompat.app.AppCompatActivity;
import java.lang.reflect.Method;
import Android.telephony.ITelephony;

public class ModeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);
        //获取按钮
        Button back = findViewById(R.id.back);
        Switch ModeSwitch=findViewById(R.id.ModeSwitch);
        //设置按钮点击监听器
        back.setOnClickListener(this);
        ModeSwitch.setOnClickListener(this);
    }

    private void  end(){
        AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        TelephonyManager tm = (TelephonyManager) getSystemService(Service.TELEPHONY_SERVICE);
        tm.listen(new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                super.onCallStateChanged(state, phoneNumber);
                Log.e("onCallStateChanged","state = "+state +"  phoneNumber = "+phoneNumber);
                if(state==TelephonyManager.CALL_STATE_RINGING){
                    am.setRingerMode(AudioManager.RINGER_MODE_SILENT);//设置为静音模式，解决延迟导致的响铃问题
                    endCall();
                }
            }
        },PhoneStateListener.LISTEN_CALL_STATE);
    }

    private void  open(){
        AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        TelephonyManager tm = (TelephonyManager) getSystemService(Service.TELEPHONY_SERVICE);
        tm.listen(new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                super.onCallStateChanged(state, phoneNumber);
                Log.e("onCallStateChanged","state = "+state +"  phoneNumber = "+phoneNumber);
                if(state==TelephonyManager.CALL_STATE_RINGING){
                    am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);//恢复普通模式，恢复声音
                }
            }
        },PhoneStateListener.LISTEN_CALL_STATE);
    }

    private void endCall() {
        try {
            //通过类加载器加载ServiceManager
            Class<?> clazz = getClassLoader().loadClass("android.os.ServiceManager");
            //通过反射得到当前的方法
            Method method = clazz.getDeclaredMethod("getService", String.class);
            IBinder iBinder = (IBinder) method.invoke(null, TELEPHONY_SERVICE);
            ITelephony iTelephony = ITelephony.Stub.asInterface(iBinder);
            iTelephony.endCall();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.ModeSwitch:
                Switch ModeSwitch=findViewById(R.id.ModeSwitch);
                String str="";
                if(ModeSwitch.isChecked()==true){
                    str="勿扰模式开启";
                    end();
                }else{
                    str="勿扰模式关闭";
                    open();
                }
                Toast.makeText(ModeActivity.this, str , Toast.LENGTH_SHORT).show();
                break;
            case R.id.back:
                Intent intent2 = new Intent(this, SettingActivity.class);
                startActivity(intent2);
                finish();
        }
    }
}
