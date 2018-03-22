package com.trungdang.practice.mindorks_dagger2example.di.components;

import android.app.Application;
import android.content.Context;

import com.trungdang.practice.mindorks_dagger2example.DemoApplication;
import com.trungdang.practice.mindorks_dagger2example.di.ApplicationContext;
import com.trungdang.practice.mindorks_dagger2example.di.modules.ApplicationModule;
import com.trungdang.practice.mindorks_dagger2example.model.DataManager;
import com.trungdang.practice.mindorks_dagger2example.model.DbHelper;
import com.trungdang.practice.mindorks_dagger2example.model.SharedPrefsHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Trung Dang on 3/19/2018.
 *
 *
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(DemoApplication demoApplication);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    DataManager getDataManager();

    SharedPrefsHelper getPreferenceHelper();

    DbHelper getDbHelper();
}
