package com.williamtygret.marvel.MVP.model;

public class HeroesResponseDataResultsEvents {
    private HeroesResponseDataResultsEventsItems[] items;
    private String collectionURI;
    private int available;
    private int returned;

    public HeroesResponseDataResultsEventsItems[] getItems() {
        return this.items;
    }

    public void setItems(HeroesResponseDataResultsEventsItems[] items) {
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
