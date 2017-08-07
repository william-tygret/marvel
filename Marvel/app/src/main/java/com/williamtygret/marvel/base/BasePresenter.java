package com.williamtygret.marvel.base;

import com.williamtygret.marvel.MVP.model.Hero;
import com.williamtygret.marvel.MVP.model.HeroesResponseData;
import com.williamtygret.marvel.MVP.model.HeroesResponseDataResults;
import com.williamtygret.marvel.MVP.presenter.HeroPresenter;
import com.williamtygret.marvel.MVP.view.BaseView;
import com.williamtygret.marvel.mapper.HeroMapper;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by williamtygret on 8/5/17.
 */
public class BasePresenter<V extends BaseView> {

    @Inject
    protected V mView;

    @Inject
    protected HeroMapper mHeroMapper;

    protected V getView(){
        return mView;
    }

    protected <T> void subscribe(Observable<T> observable, Observer<T> observer){
        observable.subscribeOn(Schedulers.newThread())
                .toSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }




}
