//package com.sdkj.ws.activity;
//
//import android.content.Context;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.Bundle;
//import android.os.Message;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.view.KeyEvent;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.ImageButton;
//import android.widget.Toast;
//
//import com.sdkj.ws.R;
//import com.sdkj.ws.activity.fragment.CarFragment;
//import com.sdkj.ws.activity.fragment.HomeFragment;
//
///**
// * @author:wjj
// * @date: 2017/7/7 15:16.
// * @description:
// */
//
//public class IndexAty extends BaseAty {
//
//    private Context context = IndexAty.this;
//
//    private ImageButton imgHome;
//    private ImageButton imageCar;
//    private ImageButton imgbtn3;
//    private ImageButton imgbtn4;
//    private long exitTime = 0;
//
//    HomeFragment homeFragment = null;
//    CarFragment carFragment = null;
//
//    // 判断是否联网
//    public static Boolean isNetworkConnected(Context context) {
//        ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext()
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (manager == null) {
//            return false;
//        }
//        NetworkInfo networkinfo = manager.getActiveNetworkInfo();
//        if (networkinfo == null || !networkinfo.isAvailable()) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_index);
//        homeFragment = new HomeFragment(context);
//        carFragment = new CarFragment(context);
//        init();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
//        beginTransaction.add(R.id.mainlayout, homeFragment);
//        imgHome.setBackgroundResource(R.drawable.home_hit);
//        beginTransaction.commit();
//
////        if (!isNetworkConnected(getApplicationContext())) {
////            State.online_flag = 0;
////            Toast.makeText(getApplicationContext(), "当前网络未连接", 0).show();
////        } else {
////            State.online_flag = 1;
////        }
//
//    }
//
//    @Override
//    public void handlerMessage(Message msg) {
//
//    }
//
//    private void init() {
//        imgHome = (ImageButton) findViewById(R.id.image_home);
//        imageCar = (ImageButton) findViewById(R.id.image_car);
//        imgHome.setOnClickListener(click);
//        imageCar.setOnClickListener(click);
//    }
//
//    OnClickListener click = new OnClickListener() {
//
//        @Override
//        public void onClick(View arg0) {
//            FragmentManager fm = getSupportFragmentManager();
//            FragmentTransaction bt = fm.beginTransaction();
//            resetImg();
//            switch (arg0.getId()) {
//                case R.id.image_home:
//                    bt.replace(R.id.mainlayout, homeFragment);
//                    imgHome.setBackgroundResource(R.drawable.home_hit);
//                    bt.commit();
//                    break;
//                case R.id.image_car:
//                    bt.replace(R.id.mainlayout, carFragment);
//                    imageCar.setBackgroundResource(R.drawable.car_hit);
//                    bt.commit();
//                    break;
////                case R.id.imageButton3:
////                    if (UserState.flag == 0) {
////                        Intent intent = new Intent();
////                        intent.setClass(MainActivity.this, LoginActivity.class);
////                        startActivity(intent);
////                    } else {
////                        bt.replace(R.id.mainlayout, cartFragment);
////                        imgbtn3.setBackgroundResource(R.drawable.car_hit);
////                        bt.commit();
////                    }
////                    break;
////                case R.id.imageButton4:
////                    if (UserState.flag == 0) {
////                        Intent intent = new Intent();
////                        intent.setClass(MainActivity.this, LoginActivity.class);
////                        startActivity(intent);
////                    } else {
////                        bt.replace(R.id.mainlayout, selfFragment);
////                        imgbtn4.setBackgroundResource(R.drawable.user_hit);
////                        bt.commit();
////                    }
////                    break;
//                default:
//                    break;
//            }
//        }
//    };
//
//    public void resetImg() {
//        imgHome.setBackgroundResource(R.drawable.home_gray);
//        imageCar.setBackgroundResource(R.drawable.car_gray);
//        //imgbtn3.setBackgroundResource(R.drawable.car_gray);
//        //imgbtn4.setBackgroundResource(R.drawable.user_gray);
//    }
//
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            exit();
//            exitTime = System.currentTimeMillis();
//            return false;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    public void exit() {
//        if ((System.currentTimeMillis() - exitTime) > 2000) {
//            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
//        } else {
//            System.exit(0);
//        }
//    }
//}
//
