package com.sdkj.ws.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.sdkj.ws.R;
import com.sdkj.ws.activity.Bavigation_MainAty;

/**
 * @author:wjj
 * @date: 2017/6/28 10:13.
 * @description:
 */

public class AD_WelcomeAty extends Activity {

    // 声明控件对象
    private TextView textView;
    private int count = 3;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去除标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ad_welcome);
        // 初始化控件对象
        textView = (TextView) findViewById(R.id.textView);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_text);
        //textView.startAnimation(animation);
        handler.sendEmptyMessageDelayed(0, 1000);

    }

    private int getCount() {
        count--;
        if (count == 0) {
            //Intent intent = new Intent(this, IndexAty.class);
            Intent intent = new Intent(this, Bavigation_MainAty.class);

            startActivity(intent);
            finish();
        }
        return count;
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                textView.setText(getCount() + "");
                handler.sendEmptyMessageDelayed(0, 1000);
                animation.reset();
                textView.startAnimation(animation);
            }

        }

        ;

    };

}