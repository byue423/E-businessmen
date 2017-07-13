package org.mf.startupadpage;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

import org.mf.startupadpage.entity.BnfStartupAdPageDto;

import java.io.File;

import cn.aigestudio.downloader.bizs.DLManager;
import cn.aigestudio.downloader.interfaces.DLTaskListener;

public class MainActivity extends Activity {


    private String AD_PATH
            = Environment.getExternalStorageDirectory() + "/MFAd/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BnfStartupAdPageDto bnfStartupAdPageDto = new BnfStartupAdPageDto();
        bnfStartupAdPageDto.setPicUrl("http://watermelon-pic-prod.b0.upaiyun.com/StartupAdPage/201512251628492795420.png");
        bnfStartupAdPageDto.setDisplaySecond(3);
        AdPreference.getInstance().saveStartupAdPage(bnfStartupAdPageDto);
        initAdData();
    }

    private void initAdData() {
        String AD_PATH
                = Environment.getExternalStorageDirectory() + "/MFAd/" + Utils.getImgName(AdPreference.getInstance().getStartupAdPage().getPicUrl());

        File file = new File(AD_PATH);
        if (!file.exists()) {
            DLManager.getInstance(this).dlStart(AdPreference.getInstance().getStartupAdPage().getPicUrl(), this.AD_PATH, new DLTaskListener() {

                @Override
                public void onError(String error) {
                    super.onError(error);
                    AdPreference.getInstance().clear();
                }

            });
        }
    }

}
