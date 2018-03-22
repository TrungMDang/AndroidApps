package com.trungdang.practice.mindorks_dagger2example.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.trungdang.practice.mindorks_dagger2example.di.ApplicationContext;
import com.trungdang.practice.mindorks_dagger2example.di.DatabaseInfo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Trung Dang on 3/19/2018.
 *
 * Dependency provider. Provides necessary dependency objects from this graph:
 * <p>------------------------Application--------------------------------</p>
 * <p>------------------------DataManager--------------------------------</p>
 * <p>Context----------dbName------------version--------SharedPrefsHelper</p>
 * <p>-----------------------------------------------------------------SharedPreferences</p>
 *
 * Objects will be: Context, dbName, version, and SharedPreferences
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application app) {
        mApplication = app;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return "demo-dagger.db";
    }

    @Provides
    @DatabaseInfo
    Integer provideDatabaseVersion() {
        return 2;
    }

    @Provides
    SharedPreferences provideSharedPrefs() {
        return mApplication.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE);
    }
}
