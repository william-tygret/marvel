package com.williamtygret.marvel.API;

import android.app.SearchManager;
import android.os.Bundle;
import android.util.Log;

import com.williamtygret.marvel.MVP.model.HeroesResponse;
import com.williamtygret.marvel.MVP.model.HeroesResponseData;
import com.williamtygret.marvel.MVP.model.HeroesResponseDataResults;
import com.williamtygret.marvel.MVP.model.Query;
import com.williamtygret.marvel.modules.home.MainActivity;

import junit.framework.Test;

import javax.inject.Inject;

import retrofit2.http.GET;
import rx.Observable;

import static android.content.Intent.getIntent;

/**
 * Created by williamtygret on 8/5/17.
 */
public interface HeroAPIService  {

//    @Inject
//    Query message = new Query();
//    String searchQuery = message.getSearchQuery();

    String query = "spi";


    @GET("/v1/public/characters?nameStartsWith="+query+"&apikey=fa443fc4b33f7237765dc9a31c13aa7c&ts=12345&hash=bb42fc23b33291300948ce0bfa64a6b3")
    Observable<HeroesResponse> getHeroes();

}
