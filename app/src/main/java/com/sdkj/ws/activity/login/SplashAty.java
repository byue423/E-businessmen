package com.sdkj.ws.activity.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.sdkj.ws.R;

/**
 * @author:wjj
 * @date: 2017/6/28 9:22.
 * @description:
 */

public class SplashAty extends Activity {
    boolean First = false;
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;
    // 延迟3秒
    //private static final long SPLASH_DELAY_MILLIS = 3000;
    private static final long SPLASH_DELAY_MILLIS = 100;

    /**
     * Handler:跳转到不同界面
     */
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrance);
        init();
    }

    private void init() {

        // 取得相应的值，如果没有该值，说明还未写入，用true作为默认值
        // 判断程序第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
        SharedPreferences share = getSharedPreferences("First", MODE_PRIVATE);
        String s = share.getString("FirstEntrance", null);
        if (First || !TextUtils.isEmpty(s)) {
            // 使用Handler的postDelayed方法，2秒后执行跳转到主activity
            mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);

        } else {
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
        }

    }

    private void goHome() {
        //Intent intent = new Intent(SplashAty.this, IndexAty.class);
        Intent intent = new Intent(SplashAty.this, AD_WelcomeAty.class);
        this.startActivity(intent);
        this.finish();
    }

    private void goGuide() {
        Intent intent = new Intent(SplashAty.this, WelcomeAty.class);
        SplashAty.this.startActivity(intent);
        SharedPreferences sharedPreference = getSharedPreferences("First",
                MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString("FirstEntrance", "FirstEntrance");
        editor.commit();
        this.finish();
    }
}
