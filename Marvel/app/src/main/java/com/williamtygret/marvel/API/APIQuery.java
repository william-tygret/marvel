package com.williamtygret.marvel.API;

import android.util.Log;

import com.williamtygret.marvel.MVP.model.HeroesResponse;
import com.williamtygret.marvel.modules.home.MainActivity;

import rx.Observable;

/**
 * Created by williamtygret on 8/7/17.
 */
public class APIQuery extends MainActivity {

    protected String getQuerySearch(String message) {
        mSearchQuery = message;
        Log.d("searchquery","search query is: "+getQuerySearch(message));
        return message;
    }

}
