package com.williamtygret.marvel.mapper;

import android.util.Log;

import com.williamtygret.marvel.MVP.model.Hero;
import com.williamtygret.marvel.MVP.model.HeroesResponse;
import com.williamtygret.marvel.MVP.model.HeroesResponseData;
import com.williamtygret.marvel.MVP.model.HeroesResponseDataResults;
import com.williamtygret.marvel.MVP.model.Storage;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by williamtygret on 8/5/17.
 */
public class HeroMapper {


    @Inject
    public HeroMapper() {

    }

    public List<Hero> mapHeroes(Storage storage, HeroesResponse heroesResponse) {
        List<Hero> heroList = new ArrayList<>();
        Log.d("heroesResponse","heroes response is: "+heroesResponse);
        HeroesResponseData heroesResponseData = heroesResponse.getData();


        if (heroesResponse != null) {
            HeroesResponseDataResults[] responseData = heroesResponseData.getResults();
            Log.d("responseData","the response data is: "+responseData);
            if (responseData != null) {
                HeroesResponseDataResults[] responseDataResultses = responseData;
                for(HeroesResponseDataResults hero : responseDataResultses) {
                    Hero myHero = new Hero();
                    myHero.setId(hero.getId());
                    myHero.setName(hero.getName());
                    myHero.setDescription(hero.getDescription());
                    myHero.setThumbnail(hero.getThumbnail().getPath());
                    myHero.setResourceURI(hero.getResourceURI());
                    storage.addHero(myHero);
                    //Log.d("savedheroes","saved heroes are: "+storage.getSavedHeroes());
                    heroList.add(myHero);
                    Log.d("myhero", "Myhero is: " + myHero);
                }

            }
        }

        return heroList;
        }


    }


