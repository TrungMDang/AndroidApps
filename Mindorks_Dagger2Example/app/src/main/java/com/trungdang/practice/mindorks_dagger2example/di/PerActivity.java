package com.trungdang.practice.mindorks_dagger2example.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Trung Dang on 3/19/2018.
 *
 * <p>"@Scope" Means for class A that has member variables injected by class B
 * which is marked with @Scope, every time an instance of class A asking
 * for dependency objects from B will get its own set of member variables.</p>
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
