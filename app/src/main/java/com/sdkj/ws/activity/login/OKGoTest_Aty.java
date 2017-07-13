package com.sdkj.ws.activity.login;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.byue.okgo.tools.WebConfig;
import com.byue.okgo.tools.WebService;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.sdkj.ws.R;
import com.sdkj.ws.activity.BaseAty;
import com.sdkj.ws.activity.ImageViewAty;
import com.util.DisplayUtil;
import com.util.StringEmpty;
import com.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OKGoTest_Aty extends BaseAty {
    private Context context = OKGoTest_Aty.this;

    private TextView tv;
    //private Handler mHandler = new BaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fresco.initialize(context);
        setContentView(R.layout.main);

        int dip2px = DisplayUtil.dip2px(context, 200);
        int margin = DisplayUtil.dip2px(context, 12);

        Button jk = (Button) findViewById(R.id.jk);
        tv = (TextView) findViewById(R.id.tx);
        final String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498629973724&di=51879497d7373d9d150e5b9d49dcf7cc&imgtype=0&src=http%3A%2F%2Fs3.lvjs.com.cn%2Ftrip%2Foriginal%2F20140818131550_1792868513.jpg";
        Uri uri = Uri.parse(url);
        LinearLayout layout = (LinearLayout)findViewById(R.id.v_lay);

        for (int i = 0; i < 3; i++){
            SimpleDraweeView sv = new SimpleDraweeView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dip2px,dip2px);
            params.setMargins(margin,margin,margin,margin);
            sv.setLayoutParams(params);

//            sv.setMinimumWidth(100);
//            sv.setMaxWidth(110);
//            sv.setMinimumHeight(100);
//            sv.setMaxHeight(110);
            sv.setId(i + 10);
            sv.setImageURI(uri);
            layout.addView(sv);
        }

        //Uri uri = Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498629973724&di=51879497d7373d9d150e5b9d49dcf7cc&imgtype=0&src=http%3A%2F%2Fs3.lvjs.com.cn%2Ftrip%2Foriginal%2F20140818131550_1792868513.jpg");
        final SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.image_view);
        draweeView.setImageURI(uri);
        draweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OKGoTest_Aty.this, ImageViewAty.class);
                intent.putExtra("img_url", url);
                startActivity(intent);
            }
        });

        jk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("username", "jiangdongyi");
            map.put("password", "111111");
            Object obj = (Object) map;

            WebService wb = new WebService(context, WebConfig.POST, WebConfig.USERLOGIN, obj, WebConfig.GET_LOGIN);
            new Thread(wb).start();

            }
        });
    }

    //处理子线程反馈的信息
    @Override
    public void handlerMessage(Message msg) {

        dismissLoading();
        //处理与子线程的交互逻辑
        String jsonString = (String) msg.obj;
        Gson gson = new Gson();

        if (StringEmpty.isGoodJson(jsonString)) {
            try {
                JSONObject jsonObj = new JSONObject(jsonString);
                String resultCode = jsonObj.getString("resultCode");
                if ("0".equals(resultCode)) {
                    switch (msg.what) {
                        case WebConfig.GET_LOGIN:
                            ToastUtil.toast(context, "登录成功");
                            break;
                        case WebConfig.ERROR_MSG:
                            ToastUtil.toast(context, jsonString);
                            break;
                    }
                } else {
                    ToastUtil.toast(context, jsonString);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            ToastUtil.toast(context, jsonString);
        }

    }
}
