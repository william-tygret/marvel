package com.williamtygret.marvel.MVP.model;

public class HeroesResponseDataResultsSeries {
    private HeroesResponseDataResultsSeriesItems[] items;
    private String collectionURI;
    private int available;
    private int returned;

    public HeroesResponseDataResultsSeriesItems[] getItems() {
        return this.items;
    }

    public void setItems(HeroesResponseDataResultsSeriesItems[] items) {
        this.items = items;
    }

    public String getCollectionURI() {
        return this.collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public int getAvailable() {
        return this.available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getReturned() {
        return this.returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }
}
