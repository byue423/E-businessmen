package com.sdkj.ws.activity.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.sdkj.ws.R;
import com.sdkj.ws.activity.entity.AdDomain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SuppressLint("ValidFragment")
public class HomeFragment extends Fragment {
    // 广告的数据
    private ViewPager adViewPager;
    private List<ImageView> imageViews;// 滑动的图片集合
    private List<View> dots; // 图片标题正文的那些点
    private List<View> dotList;
    //private TextView tv_date;
    //private TextView tv_title;
    //private TextView tv_topic_from;
    //private TextView tv_topic;
    //private TextView tv_image_description;
    private int currentItem = 0; // 当前图片的索引号
    // 定义的五个指示点
    private View dot0;
    private View dot1;
    private View dot2;
    private View dot3;
    private View dot4;
    private ScheduledExecutorService scheduledExecutorService;
    // 异步加载图片
    private ImageLoader mImageLoader;
    private DisplayImageOptions ad_options;
    private DisplayImageOptions pro_options;
    // 轮播banner的数据
    private List<AdDomain> adList;

    // 推荐的商品列表数据
    //List<HomeProduct> hProducts;

  //  private RefreshListView homeList;// 商品推荐列表
   // private int showCount = 3;// 展示的商品行数量

    public Context context = null;

    private EditText searchTxet;
    private Button qr_code;

    public HomeFragment(Context c) {
        // TODO Auto-generated constructor stub
        this.context = c;
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            adViewPager.setCurrentItem(currentItem);
        };
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initSearch(view);
        initAD(view);
        //initHomeList(view);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        /*if (resultCode == -1) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");

            if (scanResult.length() >= 1 & scanResult.length() <= 3) {
                int ID = Integer.parseInt(scanResult);
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("id", ID);
                startActivity(intent);
            } else {
                Intent intent = new Intent(context, SearchResultActivity.class);
                State.search_flag = 1;
                Toast.makeText(context, "搜索不到符合的商品", 0).show();
                startActivity(intent);

            }
        }*/
    }

    private void initSearch(View view) {
       /* // TODO Auto-generated method stub
        searchTxet = (EditText) view.findViewById(R.id.search_text);
        qr_code = (Button) view.findViewById(R.id.QRcode);

        qr_code.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                State.search_flag = 0;
                Intent openCameraIntent = new Intent(context, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });

        searchTxet.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SearchResultActivity.class);
                startActivity(intent);
            }
        });*/
    }

    private void initAD(View view) {
        // TODO Auto-generated method stub

        // 获取图片加载实例
        mImageLoader = ImageLoader.getInstance();
        ad_options = new DisplayImageOptions.Builder().showStubImage(R.drawable.top_banner_android)
                .showImageForEmptyUri(R.drawable.top_banner_android).showImageOnFail(R.drawable.top_banner_android)
                .cacheInMemory(true).cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY).build();
        initAdData(view);
        startAd();

    }

    private void initAdData(View v) {
        // 广告数据
        //adList = getBannerAd();
        adList = getBannerAdTest();

        imageViews = new ArrayList<ImageView>();

        // 点
        dots = new ArrayList<View>();
        dotList = new ArrayList<View>();
        dot0 = v.findViewById(R.id.v_dot0);
        dot1 = v.findViewById(R.id.v_dot1);
        dot2 = v.findViewById(R.id.v_dot2);
        dot3 = v.findViewById(R.id.v_dot3);
        dot4 = v.findViewById(R.id.v_dot4);
        dots.add(dot0);
        dots.add(dot1);
        dots.add(dot2);
        dots.add(dot3);
        dots.add(dot4);

        //tv_date = (TextView) v.findViewById(R.id.tv_date);
        //tv_title = (TextView) v.findViewById(R.id.tv_title);
        //tv_topic_from = (TextView) v.findViewById(R.id.tv_topic_from);
        //tv_topic = (TextView) v.findViewById(R.id.tv_topic);
        //tv_image_description = (TextView) v.findViewById(R.id.tv_image_description);

        adViewPager = (ViewPager) v.findViewById(R.id.vp);
        AdAdapter adAdapter = new AdAdapter();
        if (adViewPager == null)
            System.out.println("null");
        adViewPager.setAdapter(adAdapter);// 设置填充ViewPager页面的适配器
        // 设置一个监听器，当ViewPager中的页面改变时调用
        adViewPager.setOnPageChangeListener(new MyPageChangeListener());
        addDynamicView();
    }

    private void addDynamicView() {
        // 动态添加图片和下面指示的圆点
        // 初始化图片资源
        for (int i = 0; i < adList.size(); i++) {
            ImageView imageView = new ImageView(context);
            // 异步加载图片
            mImageLoader.displayImage(adList.get(i).getImgUrl(), imageView, ad_options);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
            dots.get(i).setVisibility(View.VISIBLE);
            dotList.add(dots.get(i));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void startAd() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2, TimeUnit.SECONDS);
    }

    public class ScrollTask implements Runnable {

        @Override
        public void run() {
            synchronized (adViewPager) {
                currentItem = (currentItem + 1) % imageViews.size();
                handler.obtainMessage().sendToTarget();
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        // 当Activity不可见的时候停止切换
        scheduledExecutorService.shutdown();
    }

    public class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        private int oldPosition = 0;

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            AdDomain adDomain = adList.get(position);
           // tv_title.setText(adDomain.getTitle()); // 设置标题
            //tv_date.setText(adDomain.getDate());
            //tv_topic_from.setText(adDomain.getTopicFrom());
            //tv_topic.setText(adDomain.getTopic());
            //tv_image_description.setText(adDomain.getTopic());
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }
    }

    // 广告的适配器
    public class AdAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return adList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView iv = imageViews.get(position);
            ((ViewPager) container).addView(iv);
            @SuppressWarnings("unused")
            final AdDomain adDomain = adList.get(position);
            // 在这个方法里面设置图片的点击事件
            iv.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 处理跳转逻辑
//					Toast.makeText(context, adList.get(position).getTargetUrl(), 0).show();
                }
            });
            return iv;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }

    }

    // 轮播广播模拟数据
    public static List<AdDomain> getBannerAd() {
        List<AdDomain> adList = new ArrayList<AdDomain>();
        String[] imgUrls = { "http://chuantu.biz/t5/22/1469537727x3738746541.jpg",
                "http://chuantu.biz/t5/22/1469537829x3738746541.jpg",
                "http://chuantu.biz/t5/22/1469537876x3738746541.png",
                "http://chuantu.biz/t5/22/1469537904x3738746541.jpg",
                "http://chuantu.biz/t5/22/1469537922x3738746541.jpg" };
        for (int i = 1; i <= 5; i++) {

            AdDomain adDomain = new AdDomain();
            adDomain.setId("10" + i);
            adDomain.setDate("");
            adDomain.setTitle("");
            adDomain.setTopicFrom("");
            adDomain.setTopic("");
            adDomain.setImgUrl(imgUrls[i - 1]);
            adDomain.setAd(false);
            adDomain.setTargetUrl("目标地址" + i);
            adList.add(adDomain);
        }

        return adList;
    }

    /**
     * 轮播广播模拟数据
     *
     * @return
     */
    public static List<AdDomain> getBannerAdTest() {
        List<AdDomain> adList = new ArrayList<AdDomain>();

        AdDomain adDomain = new AdDomain();
        adDomain.setId("108078");
        adDomain.setDate("3月4日");
        adDomain.setTitle("我和令计划只是同姓");
        adDomain.setTopicFrom("阿宅");
        adDomain.setTopic("我想知道令狐安和令计划有什么关系？");
        adDomain.setImgUrl("http://g.hiphotos.baidu.com/image/w%3D310/sign=bb99d6add2c8a786be2a4c0f5708c9c7/d50735fae6cd7b8900d74cd40c2442a7d9330e29.jpg");
        adDomain.setAd(false);
        adList.add(adDomain);

        AdDomain adDomain2 = new AdDomain();
        adDomain2.setId("108078");
        adDomain2.setDate("3月5日");
        adDomain2.setTitle("我和令计划只是同姓");
        adDomain2.setTopicFrom("小巫");
        adDomain2.setTopic("“2我想知道令狐安和令计划有什么关系？”");
        adDomain2
                .setImgUrl("http://g.hiphotos.baidu.com/image/w%3D310/sign=7cbcd7da78f40ad115e4c1e2672e1151/eaf81a4c510fd9f9a1edb58b262dd42a2934a45e.jpg");
        adDomain2.setAd(false);
        adList.add(adDomain2);

        AdDomain adDomain3 = new AdDomain();
        adDomain3.setId("108078");
        adDomain3.setDate("3月6日");
        adDomain3.setTitle("我和令计划只是同姓");
        adDomain3.setTopicFrom("旭东");
        adDomain3.setTopic("“3我想知道令狐安和令计划有什么关系？”");
        adDomain3
                .setImgUrl("http://e.hiphotos.baidu.com/image/w%3D310/sign=392ce7f779899e51788e3c1572a6d990/8718367adab44aed22a58aeeb11c8701a08bfbd4.jpg");
        adDomain3.setAd(false);
        adList.add(adDomain3);

        AdDomain adDomain4 = new AdDomain();
        adDomain4.setId("108078");
        adDomain4.setDate("3月7日");
        adDomain4.setTitle("我和令计划只是同姓");
        adDomain4.setTopicFrom("小软");
        adDomain4.setTopic("“4我想知道令狐安和令计划有什么关系？”");
        adDomain4
                .setImgUrl("http://d.hiphotos.baidu.com/image/w%3D310/sign=54884c82b78f8c54e3d3c32e0a282dee/a686c9177f3e670932e4cf9338c79f3df9dc55f2.jpg");
        adDomain4.setAd(false);
        adList.add(adDomain4);

        AdDomain adDomain5 = new AdDomain();
        adDomain5.setId("108078");
        adDomain5.setDate("3月8日");
        adDomain5.setTitle("我和令计划只是同姓");
        adDomain5.setTopicFrom("大熊");
        adDomain5.setTopic("“5我想知道令狐安和令计划有什么关系？”");
        adDomain5
                .setImgUrl("http://e.hiphotos.baidu.com/image/w%3D310/sign=66270b4fe8c4b7453494b117fffd1e78/0bd162d9f2d3572c7dad11ba8913632762d0c30d.jpg");
        adDomain5.setAd(true); // 代表是广告
        adList.add(adDomain5);

        return adList;
    }

//    private void initHomeList(View view) {
//        homeList = (RefreshListView) view.findViewById(R.id.home_lv);
//
//        pro_options = new DisplayImageOptions.Builder().showStubImage(R.drawable.loading)
//                .showImageForEmptyUri(R.drawable.load_error).showImageOnFail(R.drawable.load_error).cacheInMemory(true)
//                .cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565).imageScaleType(ImageScaleType.EXACTLY).build();
//
//        hProducts = getHomeProduct();
//        final HomeAdapter adapter2 = new HomeAdapter();
//        homeList.setAdapter(adapter2);
//        homeList.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onLoadingMore() {
//                new AsyncTask<Void, Void, Void>() {
//
//                    @Override
//                    protected Void doInBackground(Void... params) {
//                        SystemClock.sleep(500);
//                        if (State.online_flag == 1) {
//                            if (showCount * 2 <= hProducts.size()) {
//                                showCount++;
//                            } else {
//                                System.out.println("已经没有啦");
//                            }
//                        } else {
//                            System.out.println("当前网络未连接");
//                        }
//                        return null;
//                    }
//
//                    @Override
//                    protected void onPostExecute(Void result) {
//                        if (State.online_flag == 1) {
//                            if (showCount * 2 > hProducts.size()) {
//                                Toast.makeText(context, "已经加载完啦", 0).show();
//                                showCount = hProducts.size() / 2;
//                            }
//                        } else {
//                            Toast.makeText(context, "当前网络未连接", 0).show();
//                        }
//
//                        adapter2.notifyDataSetChanged();
//                        // 控制脚布局隐藏
//                        homeList.hideFooterView();
//                    }
//                }.execute(new Void[] {});
//            }
//
//            @Override
//            public void onDownPullRefresh() {
//                new AsyncTask<Void, Void, Void>() {
//
//                    @Override
//                    protected Void doInBackground(Void... params) {
//                        SystemClock.sleep(1000);
//                        // if (showCount * 2 <= hProducts.size()) {
//                        // showCount++;
//                        // } else {
//                        // System.out.println("已经没有啦");
//                        // }
//                        return null;
//                    }
//
//                    @Override
//                    protected void onPostExecute(Void result) {
//                        // if (showCount * 2 > hProducts.size()) {
//                        // Toast.makeText(context, "已经加载完啦", 0).show();
//                        // showCount = hProducts.size() / 2;
//                        // }
//                        adapter2.notifyDataSetChanged();
//                        homeList.hideHeaderView();
//                        if (State.online_flag == 1) {
//                            Toast.makeText(context, "已经刷新啦", 0).show();
//                        } else {
//                            Toast.makeText(context, "当前网络未连接", 0).show();
//                        }
//                    }
//                }.execute(new Void[] {});
//            }
//        });
//    }
//
//    private static List<HomeProduct> getHomeProduct() {
//        List<HomeProduct> hp = new ArrayList<HomeProduct>();
//        String[] pUrls = { "http://chuantu.biz/t5/22/1469537957x3738746541.png",
//                "http://chuantu.biz/t5/22/1469537981x3738746541.png",
//                "http://chuantu.biz/t5/22/1469538011x3738746541.png",
//                "http://chuantu.biz/t5/22/1469538037x3738746541.png",
//                "http://chuantu.biz/t5/22/1469538064x3738746541.png",
//                "http://chuantu.biz/t5/22/1469538086x3738746541.png",
//                "http://chuantu.biz/t5/22/1469538127x3738746541.png",
//                "http://chuantu.biz/t5/22/1469538158x3738746541.png",
//                "http://chuantu.biz/t5/22/1469538181x3738746541.png",
//                "http://chuantu.biz/t5/22/1469538201x3738746541.png",
//                "http://chuantu.biz/t5/22/1469538224x3738746541.png",
//                "http://chuantu.biz/t5/22/1469538244x3738746541.png",
//                "http://chuantu.biz/t5/22/1469538292x3738746541.png",
//                "http://chuantu.biz/t5/22/1469538336x3738746541.png",
//                "http://chuantu.biz/t5/22/1469538364x3738746541.png",
//                "http://chuantu.biz/t5/22/1469538385x3738746541.png",
//                "http://chuantu.biz/t5/22/1469538414x3738746541.png",
//                "http://chuantu.biz/t5/22/1469538444x3738746541.png" };
//        for (int i = 0; i < 18; i++) {
//            HomeProduct product = new HomeProduct();
//            product.setType("男装");
//            product.setUrl(pUrls[i]);
//            hp.add(product);
//        }
//        return hp;
//    }
//
//    public class HomeAdapter extends BaseAdapter implements ListAdapter {
//
//        @Override
//        public int getCount() {
//            // TODO Auto-generated method stub
//            return showCount;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            // TODO Auto-generated method stub
//            return hProducts.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            // TODO Auto-generated method stub
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            // TODO Auto-generated method stub
//            int _position = position * 2;
//            convertView = View.inflate(context, R.layout.item_home_list, null);
//            ImageView imageView1 = (ImageView) convertView.findViewById(R.id.home_image1);
//            ImageView imageView2 = (ImageView) convertView.findViewById(R.id.home_image2);
//            if (position < getCount()) {
//                mImageLoader.displayImage(hProducts.get(_position).getUrl(), imageView1, pro_options);
//                mImageLoader.displayImage(hProducts.get(_position + 1).getUrl(), imageView2, pro_options);
//            }
//            imageView1.setOnClickListener(new HomeClick(_position));
//            imageView2.setOnClickListener(new HomeClick(_position + 1));
//            return convertView;
//        }
//    }
//
//    private class HomeClick implements OnClickListener {
//        int position = 0;
//
//        public HomeClick(int p) {
//            this.position = p;
//        }
//
//        @Override
//        public void onClick(View v) {
//            if (State.online_flag == 1) {
////				Toast toast = Toast.makeText(context, "select the " + (position + 1), 0);
////				toast.show();
//                Intent intent = new Intent(context, GoodListActivity.class);
//                intent.putExtra("key", hProducts.get(position).getType());
//                startActivity(intent);
//            } else {
//                Toast.makeText(context, "当前网络未连接", 0).show();
//            }
//
//        }
//    }
}
