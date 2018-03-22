package com.trungdang.practice.mindorks_dagger2example;

import android.app.Application;
import android.content.Context;

import com.trungdang.practice.mindorks_dagger2example.di.components.ApplicationComponent;
import com.trungdang.practice.mindorks_dagger2example.di.components.DaggerApplicationComponent;
import com.trungdang.practice.mindorks_dagger2example.di.modules.ApplicationModule;
import com.trungdang.practice.mindorks_dagger2example.model.DataManager;

import javax.inject.Inject;

/**
 * Created by Trung Dang on 3/19/2018.
 */

public class DemoApplication extends Application {

    protected ApplicationComponent applicationComponent;

    @Inject
    DataManager dataManager;

    public static DemoApplication get(Context context) {
        return (DemoApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getComponent(){
        return applicationComponent;
    }

}
