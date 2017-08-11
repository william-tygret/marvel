package com.williamtygret.marvel.DependencyInjection.module;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by williamtygret on 8/5/17.
 */
@Module
public class ApplicationModule {

    private String mBaseURL;
    private Context mContext;

    public ApplicationModule(Context context, String baseURL){
        mBaseURL=baseURL;
        mContext = context;
    }

    @Singleton
    @Provides
    GsonConverterFactory provideGSONConverterFactory(){
        GsonConverterFactory factory = GsonConverterFactory.create();
        return factory;
    }

    @Singleton
    @Provides
    @Named("ok-1")
    OkHttpClient provideOkHttpClient1(){
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    @Named("ok-2")
    OkHttpClient provideOkHttpClient2(){
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    RxJavaCallAdapterFactory provideRxJaveCallAdapterFactory(){
        return RxJavaCallAdapterFactory.create();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(@Named("ok-1") OkHttpClient client, GsonConverterFactory converterFactory, RxJavaCallAdapterFactory adapterFactory){
        return new Retrofit.Builder()
                .baseUrl(mBaseURL)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    Context provideContext(){
        return mContext;
    }


}
