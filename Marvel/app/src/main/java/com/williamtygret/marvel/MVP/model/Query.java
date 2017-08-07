package com.williamtygret.marvel.MVP.model;

import rx.Observable;

/**
 * Created by williamtygret on 8/6/17.
 */
public class Query {
    public static Observable<Query> String;
    private String searchQuery;

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
}
