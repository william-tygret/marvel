package com.williamtygret.marvel.DependencyInjection.components;

import android.content.Context;

import com.williamtygret.marvel.DependencyInjection.module.ApplicationModule;
import com.williamtygret.marvel.application.HeroApplication;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by williamtygret on 8/5/17.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Retrofit exposeRetrofit();

    Context exposeContext();

}
