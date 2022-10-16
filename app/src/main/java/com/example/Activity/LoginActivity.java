package com.example.Activity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.others.Code;
import com.example.service.DatabaseHelper;
import com.example.service.UserService;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private String realCode;
    private DatabaseHelper databaseHelper;
    private TextView mTvLoginactivityRegister;
    private EditText mEtLoginactivityUsername;
    private EditText mEtLoginactivityPassword;
    private Button mBtLoginactivityLogin;
    private EditText mEtloginactivityPhonecodes;
    private ImageView mIvloginactivityShowcode;
    public static String username;
    public static String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);//即activity_login.xml
        InitView();
        databaseHelper=new DatabaseHelper(this);
        //将验证码用图片的形式显示出来
        mIvloginactivityShowcode.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();
    }

    private void InitView() {
        // 初始化控件
        mBtLoginactivityLogin = findViewById(R.id.bt_loginactivity_login);
        mTvLoginactivityRegister = findViewById(R.id.tv_loginactivity_register);
        mEtLoginactivityUsername = findViewById(R.id.et_loginactivity_username);
        mEtLoginactivityPassword = findViewById(R.id.et_loginactivity_password);
        mEtloginactivityPhonecodes = findViewById(R.id.et_loginactivity_phoneCodes);
        mIvloginactivityShowcode = findViewById(R.id.iv_loginactivity_showCode);

        // 设置点击事件监听器
        mBtLoginactivityLogin.setOnClickListener(this);
        mIvloginactivityShowcode.setOnClickListener(this);
        mTvLoginactivityRegister.setOnClickListener(this);
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
            case R.id.tv_loginactivity_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                break;
            case R.id.iv_loginactivity_showCode:
                mIvloginactivityShowcode.setImageBitmap(Code.getInstance().createBitmap());
                realCode = Code.getInstance().getCode().toLowerCase();
                break;
            case R.id.bt_loginactivity_login:
                username = mEtLoginactivityUsername.getText().toString().trim();
                password = mEtLoginactivityPassword.getText().toString().trim();
                String phoneCode = mEtloginactivityPhonecodes.getText().toString().toLowerCase();

                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)&&!TextUtils.isEmpty(phoneCode)) {
                    if (phoneCode.equals(realCode)) {
                        UserService uService = new UserService(LoginActivity.this);
                        boolean flag = uService.login(username, password);
                        if (flag) {
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();//销毁此Activity
                        } else {
                            Toast.makeText(LoginActivity.this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(LoginActivity.this, "验证码错误,注册失败", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
                }
            }
    }
}
