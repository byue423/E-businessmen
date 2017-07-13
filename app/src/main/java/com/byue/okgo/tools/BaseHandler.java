package com.byue.okgo.tools;

import android.os.Handler;
import android.os.Message;

import com.sdkj.ws.activity.BaseAty;

import java.lang.ref.WeakReference;

/**
 * @author:wjj
 * @date: 2017/6/27 15:03.
 * @description:
 */
public class BaseHandler extends Handler {

    private WeakReference<BaseAty> reference;

    public BaseHandler(BaseAty activity) {
        reference = new WeakReference<BaseAty>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        if (reference != null && reference.get() != null) {
            reference.get().handlerMessage(msg);
        }
    }

}
