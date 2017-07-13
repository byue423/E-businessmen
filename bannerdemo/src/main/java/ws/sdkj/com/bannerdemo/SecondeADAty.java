package ws.sdkj.com.bannerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wjj
 * @date: 2017/7/4 11:11.
 * @description:
 */

public class SecondeADAty extends Activity {

    private List<ImageView> mImageList;
    /**
     * 广告条正下方的标语
     */
    private String[] imageDescriptionArray = { //
            "巩俐不低俗，我就不能低俗", //
            "扑树又回来啦！再唱经典老歌引万人大合唱", //
            "揭秘北京电影如何升级", //
            "乐视网TV版大派送", //
            "热血屌丝的反杀"};
    /**
     * 记录上一次点的位置，默认为0
     */
    private int previousPointEnale = 0;
    private ViewPager mViewPager;
    private LinearLayout llPointGroup;
    private TextView tvDescription;
    /**
     * 记录是否停止循环播放
     */
    private boolean isStop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        // 开启子线程，让广告条以2秒的频率循环播放
        new Thread(new Runnable() {

            @Override
            public void run() {

                while (!isStop) {
                    SystemClock.sleep(2000);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                        }
                    });
                }
            }
        }).start();
    }

    private void init() {
        llPointGroup = (LinearLayout) findViewById(R.id.ll_point_group);
        tvDescription = (TextView) findViewById(R.id.tv_image_description);
        mImageList = new ArrayList<ImageView>();
        int[] imageIds = new int[]{R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};
        ImageView mImageView;
        LayoutParams params;
        // 初始化广告条资源
        for (int id : imageIds) {
            mImageView = new ImageView(this);
            mImageView.setBackgroundResource(id);
            mImageList.add(mImageView);

            // 初始化广告条正下方的"点"
            View dot = new View(this);
            dot.setBackgroundResource(R.drawable.point_background);
            params = new LayoutParams(5, 5);
            params.leftMargin = 10;
            dot.setLayoutParams(params);
            dot.setEnabled(false);
            llPointGroup.addView(dot);
        }
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new MyAdapter());

        // 设置广告标语和底部“点”选择状态
        tvDescription.setText(imageDescriptionArray[0]);
        llPointGroup.getChildAt(0).setEnabled(true);

        // 设置广告条跳转时，广告语和状态语的变化
        mViewPager.setOnPageChangeListener(new MyListener());

        // 初始化广告条，当前索引Integer.MAX_VALUE的一半
        int index = (Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2 % mImageList.size());
        mViewPager.setCurrentItem(index); // 设置当前选中的Page，会触发onPageChangListener.onPageSelected方法
    }

    private class MyListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int arg0) {
            // 获取新的位置
            int newPosition = arg0 % imageDescriptionArray.length;
            // 设置广告标语
            tvDescription.setText(imageDescriptionArray[newPosition]);
            // 消除上一次的状态点
            llPointGroup.getChildAt(previousPointEnale).setEnabled(false);
            // 设置当前的状态点“点”
            llPointGroup.getChildAt(newPosition).setEnabled(true);
            // 记录位置
            previousPointEnale = newPosition;
        }

    }

    /**
     * ViewPager数据适配器
     */
    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            // 将viewpager页数设置成Integer.MAX_VALUE，可以模拟无限循环
            return Integer.MAX_VALUE;
        }

        /**
         * 复用对象 true 复用view false 复用的是Object
         */
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        /**
         * 销毁对象
         *
         * @param position 被销毁对象的索引位置
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mImageList.get(position % mImageList.size()));
        }

        /**
         * 初始化一个对象
         *
         * @param position 初始化对象的索引位置
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageList.get(position % mImageList.size()));
            return mImageList.get(position % mImageList.size());
        }

    }

    @Override
    protected void onDestroy() {
        // activity销毁时候，关闭循环播放
        isStop = true;
        super.onDestroy();
    }

}
