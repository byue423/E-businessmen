package org.mf.startupadpage;

import android.content.Context;
import android.content.SharedPreferences;

import org.mf.startupadpage.entity.BnfStartupAdPageDto;

/**
 * Created by mafei on 15/11/30.
 */
public class AdPreference {

    private static final String StartupAdPage = "StartupAdPage";

    private static Context mContext;

    public AdPreference(Context context) {
        mContext = context;
    }

    private static AdPreference adPreference = new AdPreference(mContext);

    public static synchronized AdPreference getInstance() {
        return adPreference;
    }

    public void saveStartupAdPage(BnfStartupAdPageDto bnfStartupAdPageDto) {
        SharedPreferences preferences = mContext.getSharedPreferences(StartupAdPage, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (bnfStartupAdPageDto.getId() != 0) {
            editor.putInt("id", bnfStartupAdPageDto.getId());
        }
        if (bnfStartupAdPageDto.getPicUrl() != null && !bnfStartupAdPageDto.getPicUrl().equals("")) {
            editor.putString("picUrl", bnfStartupAdPageDto.getPicUrl());
        }
        if (bnfStartupAdPageDto.getRelatedUrl() != null && !bnfStartupAdPageDto.getRelatedUrl().equals("")) {
            editor.putString("relatedUrl", bnfStartupAdPageDto.getRelatedUrl());
        }
        if (bnfStartupAdPageDto.getTitle() != null && !bnfStartupAdPageDto.getTitle().equals("")) {
            editor.putString("title", bnfStartupAdPageDto.getTitle());
        }
        if (bnfStartupAdPageDto.getUpdateTime() != null && !bnfStartupAdPageDto.getUpdateTime().equals("")) {
            editor.putString("updateTime", bnfStartupAdPageDto.getUpdateTime());
        }
        if (bnfStartupAdPageDto.getDisplaySecond() != 0) {
            editor.putInt("displaySecond", bnfStartupAdPageDto.getDisplaySecond());
        }
        editor.apply();
    }

    public BnfStartupAdPageDto getStartupAdPage() throws ClassCastException {
        SharedPreferences preferences = mContext.getSharedPreferences(StartupAdPage, Context.MODE_PRIVATE);
        BnfStartupAdPageDto bnfStartupAdPageDto = new BnfStartupAdPageDto();
        bnfStartupAdPageDto.setId(preferences.getInt("id", 0));
        bnfStartupAdPageDto.setPicUrl(preferences.getString("picUrl", ""));
        bnfStartupAdPageDto.setRelatedUrl(preferences.getString("relatedUrl", ""));
        bnfStartupAdPageDto.setTitle(preferences.getString("title", ""));
        bnfStartupAdPageDto.setUpdateTime(preferences.getString("updateTime", ""));
        bnfStartupAdPageDto.setDisplaySecond(preferences.getInt("displaySecond", 0));
        return bnfStartupAdPageDto;
    }

    public void clear() {
        SharedPreferences preferences = mContext.getSharedPreferences(StartupAdPage, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}
