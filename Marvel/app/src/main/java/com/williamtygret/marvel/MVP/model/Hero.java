package com.williamtygret.marvel.MVP.model;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by williamtygret on 8/5/17.
 */
public class Hero {

    private int id;
    private String name;
    private String description;
    private String thumbnail;
    private String resourceURI;
    private HeroesResponseDataResultsComics comics;
    private HeroesResponseDataResultsSeries series;
    private HeroesResponseDataResultsStories stories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public HeroesResponseDataResultsComics getComics() {
        return comics;
    }

    public void setComics(HeroesResponseDataResultsComics comics) {
        this.comics = comics;
    }

    public HeroesResponseDataResultsSeries getSeries() {
        return series;
    }

    public void setSeries(HeroesResponseDataResultsSeries series) {
        this.series = series;
    }

    public HeroesResponseDataResultsStories getStories() {
        return stories;
    }

    public void setStories(HeroesResponseDataResultsStories stories) {
        this.stories = stories;
    }
}
