package com.williamtygret.marvel.application;

import android.app.Application;

import com.williamtygret.marvel.DependencyInjection.components.ApplicationComponent;
import com.williamtygret.marvel.DependencyInjection.components.DaggerApplicationComponent;
import com.williamtygret.marvel.DependencyInjection.module.ApplicationModule;

/**
 * Created by williamtygret on 8/5/17.
 */
public class HeroApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this, "https://gateway.marvel.com")).build();
    }

    public ApplicationComponent getApplicationComponent(){
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
