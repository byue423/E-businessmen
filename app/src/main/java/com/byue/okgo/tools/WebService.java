package com.byue.okgo.tools;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.sdkj.ws.activity.BaseAty;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;

import java.io.IOException;
import java.util.Map;

import okhttp3.Response;

/**
 * @author:wjj
 * @date: 2017/6/27 15:33.
 * @description:使用okgo调用接口的工具类，实现了get，post，json类型的调用
 */

public class WebService implements Runnable {

    private Handler mHandler;
    private Context context;
    private String type, url;
    private Object obj;
    private int resultWhat;

    /**
     * @param context
     * @param type:                          接口类型：get/post/json
     * @param url:接口地址
     * @param obj：参数对象,没有参数的话，传null
     * @param resultWhat：返回标记，区分一个类中多个接口方法调用
     */
    public WebService(Context context, String type, String url, Object obj, int resultWhat) {
        BaseAty activity = (BaseAty) context;
        this.mHandler = new BaseHandler(activity);
        //this.mHandler = mHandler;
        this.context = context;
        this.type = type;
        this.url = url;
        this.obj = obj;
        this.resultWhat = resultWhat;
    }

    @Override
    public void run() {
        //执行耗时操作占位,请求网络；
        if (NetUtil.checkNet(context)) {
            String obj = "";
            if (type != null && !"".equals(type)) {
                if (WebConfig.GET.equals(type)) {
                    obj = getMethod();
                } else if (WebConfig.POST.equals(type)) {
                    obj = postMethod();
                } else if (WebConfig.JSON.equals(type)) {
                    obj = jsonMethod();
                }

                //向主线程发送消息,回调方法
                sendScessMsg(obj);
            } else {
                sendErrorMsg(WebConfig.POST_NULL_TYPE);
            }
        } else {
            sendErrorMsg(WebConfig.NETWORK_ERROR);
        }
    }

    private String getMethod() {
        try {
            Response response = null;
            GetRequest getRequest = OkGo.get(url).tag(this);
            if (obj != null) {
                Map<String, String> map = (Map<String, String>) obj;
                if (map != null && !map.isEmpty()) {
                    response = getRequest.params(map).execute();
                } else {
                    response = getRequest.execute();
                }
            } else {
                response = getRequest.execute();
            }
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return WebConfig.POST_ERROR;
    }

    private String postMethod() {
        try {
            Response response = null;
            PostRequest postRequest = OkGo.post(url).tag(this);
            if (obj != null) {
                Map<String, String> map = (Map<String, String>) obj;
                if (map != null && !map.isEmpty()) {
                    response = postRequest.params(map).execute();
                } else {
                    response = postRequest.execute();
                }
            } else {
                response = postRequest.execute();
            }
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return WebConfig.POST_ERROR;
    }

    private String jsonMethod() {
        try {
            Response response = null;
            if (obj != null) {
                response = OkGo.post(url)
                        .tag(this)
                        .upJson(new Gson().toJson(obj))
                        .execute();
            } else {
                return WebConfig.POST_JSON_ERROR;
            }
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return WebConfig.POST_ERROR;
    }

    public void sendScessMsg(String msgObj) {
        Message msg = Message.obtain();
        msg.what = resultWhat;
        msg.obj = msgObj;
        mHandler.sendMessage(msg);
    }

    public void sendErrorMsg(String error) {
        Message msg = Message.obtain();
        msg.what = WebConfig.ERROR_MSG;
        msg.obj = error;
        mHandler.sendMessage(msg);
    }

}
