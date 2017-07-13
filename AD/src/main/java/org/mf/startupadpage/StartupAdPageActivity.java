package org.mf.startupadpage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.mf.startupadpage.ui.TasksCompletedView;

import java.io.File;

public class StartupAdPageActivity extends Activity {
    private final static String ALBUM_PATH
            = Environment.getExternalStorageDirectory() + "/MFAd/" + Utils.getImgName(AdPreference.getInstance().getStartupAdPage().getPicUrl());

    ImageView ivAd;
    TasksCompletedView mTasksView;
    LinearLayout lvSkip;
    private int displaySecond = 3;
    private boolean skip = true;

    private int mTotalProgress;
    private int mCurrentProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startupadpage);
        init();

        initUI();

        initVariable();

        File file = new File(ALBUM_PATH);
        if (file.exists()) {
            Bitmap bm = BitmapFactory.decodeFile(ALBUM_PATH);
            //将图片显示到ImageView中
            if (bm!=null) {
                ivAd.setImageBitmap(bm);
            }else {
                Utils.deleteFile(ALBUM_PATH);
//                ivAd.setBackgroundResource(R.mipmap.welcome);
            }
        }

        ivAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToDo
            }
        });
        lvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartupAdPageActivity.this,
                        MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        new Thread(new ProgressRunable()).start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (skip) {
                    Intent intent = new Intent(StartupAdPageActivity.this,
                            MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, displaySecond * 1000);
    }

    private void initUI() {
        ivAd= (ImageView) findViewById(R.id.ivAd);
        mTasksView= (TasksCompletedView) findViewById(R.id.mTasksView);
        lvSkip= (LinearLayout) findViewById(R.id.lvSkip);
    }

    private void init() {
        if (AdPreference.getInstance().getStartupAdPage().getDisplaySecond() != 0) {
            displaySecond = AdPreference.getInstance().getStartupAdPage().getDisplaySecond();
        }
    }


    private void initVariable() {
        mTasksView.setTotalProgress(displaySecond + 1);
        mTotalProgress = displaySecond;
        mCurrentProgress = 0;
    }

    class ProgressRunable implements Runnable {

        @Override
        public void run() {
            while (mCurrentProgress <= mTotalProgress + 1) {
                try {
                    mCurrentProgress += 1;
                    mTasksView.setProgress(mCurrentProgress);
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

}