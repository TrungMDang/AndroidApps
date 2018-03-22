package com.trungdang.practice.mindorks_dagger2example.di.components;

import com.trungdang.practice.mindorks_dagger2example.MainActivity;
import com.trungdang.practice.mindorks_dagger2example.di.PerActivity;
import com.trungdang.practice.mindorks_dagger2example.di.modules.ActivityModule;

import dagger.Component;

/**
 * Created by Trung Dang on 3/19/2018.
 */

@PerActivity
@Component (dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
}
