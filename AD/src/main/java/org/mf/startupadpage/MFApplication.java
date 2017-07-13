package org.mf.startupadpage;

public class MFApplication extends android.app.Application {

    private AdPreference xgAdPreference;


    @Override
    public void onCreate() {
        super.onCreate();
        xgAdPreference = new AdPreference(this);
    }


}
