package com.williamtygret.marvel.MVP.presenter;

import com.williamtygret.marvel.API.HeroAPIService;
import com.williamtygret.marvel.MVP.model.Hero;
import com.williamtygret.marvel.MVP.model.HeroesResponse;
import com.williamtygret.marvel.MVP.model.HeroesResponseData;
import com.williamtygret.marvel.MVP.model.HeroesResponseDataResults;
import com.williamtygret.marvel.MVP.model.Storage;
import com.williamtygret.marvel.MVP.view.MainView;
import com.williamtygret.marvel.base.BaseActivity;
import com.williamtygret.marvel.base.BasePresenter;
import com.williamtygret.marvel.mapper.HeroMapper;
import com.williamtygret.marvel.modules.home.MainActivity;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by williamtygret on 8/5/17.
 */
public class HeroPresenter extends BasePresenter<MainView> implements Observer<HeroesResponse>{

    @Inject protected HeroAPIService mHeroAPIService;
    @Inject protected HeroMapper mHeroMapper;
    @Inject protected Storage mStorage;

    @Inject
    public HeroPresenter(){

    }

    public void getHeroes(){
        getView().onShowDialog("Accessing Marvel Universe...");
        Observable<HeroesResponse> heroesResponseObservable = mHeroAPIService.getHeroes();
        subscribe(heroesResponseObservable, this);

    }

    @Override
    public void onCompleted() {
        getView().onHideDialog();
        getView().onShowToast("Welcome to the Marvel Universe!");
    }

    @Override
    public void onError(Throwable e) {
        getView().onHideDialog();
        getView().onShowToast("Error! Information Denied by Professor X" + e.getMessage());
    }

    @Override
    public void onNext(HeroesResponse heroesResponse) {
        List<Hero> heroList = mHeroMapper.mapHeroes(mStorage,heroesResponse);
        getView().onClearItems();
        getView().onHeroLoaded(heroList);
    }


    public void getHeroesFromDatabase() {
        List<Hero> heroes = mStorage.getSavedHeroes();
        getView().onClearItems();
        getView().onHeroLoaded(heroes);
    }
}
