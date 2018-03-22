package com.trungdang.practice.mindorks_dagger2example.model;

import android.content.Context;
import android.content.res.Resources;

import com.trungdang.practice.mindorks_dagger2example.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Trung Dang on 3/19/2018.
 */
@Singleton
public class DataManager {

    private Context mContext;

    private DbHelper mDbHelper;

    private SharedPrefsHelper mSharedPrefsHelper;

    /**
     *
     *
     * @param context
     * @param dbHelper
     * @param sharedPrefsHelper
     */
    @Inject
    public DataManager(@ApplicationContext Context context,
                       DbHelper dbHelper,
                       SharedPrefsHelper sharedPrefsHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void saveAccessToken(String accessToken) {
        mSharedPrefsHelper.put(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, accessToken);
    }

    public String getAccessToken(){
        return mSharedPrefsHelper.get(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, null);
    }

    public Long createUser(User user) throws Exception {
        return mDbHelper.insertUser(user);
    }

    public User getUser(Long userId) throws Resources.NotFoundException, NullPointerException {
        return mDbHelper.getUser(userId);
    }
}
