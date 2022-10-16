package com.example.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Bean.Feedback;
import com.example.myapplication.R;
import com.example.service.FeedbackService;
import com.example.service.UserService;

import java.io.FileOutputStream;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {
    private String text;
    private Button back;
    private EditText info;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        initView();
    }
    private void initView() {
        back=findViewById(R.id.back);
        info=findViewById(R.id.feedback_info);
        submit=findViewById(R.id.submit);

        back.setOnClickListener(this);
        info.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back: //返回登录页面
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.submit:
                text = info.getText().toString().trim();
                FeedbackService feedbackService=new FeedbackService((FeedbackActivity.this));
                UserService userService=new UserService(FeedbackActivity.this);
                int userId=userService.search(LoginActivity.username,LoginActivity.password);
                Feedback feedback=new Feedback(userId,text);
                feedbackService.add(feedback);
                FileOutputStream fos;
                try {
                    fos=openFileOutput("feedback.txt",MODE_APPEND);
                    text=text+"\n";
                    fos.write(text.getBytes());
                    fos.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(this, "反馈成功，感谢您的意见", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                finish();
        }
    }
}
