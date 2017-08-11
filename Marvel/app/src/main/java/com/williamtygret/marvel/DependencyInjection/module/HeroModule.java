package com.williamtygret.marvel.DependencyInjection.module;

import com.williamtygret.marvel.API.HeroAPIService;
import com.williamtygret.marvel.DependencyInjection.scope.PerActivity;
import com.williamtygret.marvel.MVP.view.MainView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by williamtygret on 8/5/17.
 */
@Module
public class HeroModule {

    private MainView mView;

    public HeroModule(MainView view){
        mView = view;
    }

    @PerActivity
    @Provides
    HeroAPIService provideAPIService(Retrofit retrofit){
        return retrofit.create(HeroAPIService.class);
    }

    @PerActivity
    @Provides
    MainView provideView(){
        return mView;
    }

}
