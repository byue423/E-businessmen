package org.mf.startupadpage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import java.io.File;

public class SplashActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isFirst();

            }
        }, 1000);
    }

    private void init() {
        setContentView(R.layout.activity_appstart);
    }

    private void isFirst() {
        String AD_PATH
                = Environment.getExternalStorageDirectory() + "/MFAd/" + Utils.getImgName(AdPreference.getInstance().getStartupAdPage().getPicUrl());
        File file = new File(AD_PATH);
        Bitmap bm = BitmapFactory.decodeFile(AD_PATH);

        if (file.exists() && bm != null) {
            startActivity(new Intent(SplashActivity.this,
                    StartupAdPageActivity.class));
            finish();
        } else {

            Utils.deleteFile(AD_PATH);

            startActivity(new Intent(SplashActivity.this,
                    MainActivity.class));
            finish();
        }
    }

}