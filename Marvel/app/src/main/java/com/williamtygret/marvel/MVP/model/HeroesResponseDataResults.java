package com.williamtygret.marvel.MVP.model;

public class HeroesResponseDataResults {
    private String id;
    private HeroesResponseDataResultsSeries series;
    private HeroesResponseDataResultsThumbnail thumbnail;
    private HeroesResponseDataResultsStories stories;
    private String resourceURI;
    private String description;
    private HeroesResponseDataResultsEvents events;
    private HeroesResponseDataResultsUrls[] urls;
    private String name;
    private HeroesResponseDataResultsComics comics;
    private String modified;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HeroesResponseDataResultsSeries getSeries() {
        return this.series;
    }

    public void setSeries(HeroesResponseDataResultsSeries series) {
        this.series = series;
    }

    public HeroesResponseDataResultsThumbnail getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(HeroesResponseDataResultsThumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public HeroesResponseDataResultsStories getStories() {
        return this.stories;
    }

    public void setStories(HeroesResponseDataResultsStories stories) {
        this.stories = stories;
    }

    public String getResourceURI() {
        return this.resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HeroesResponseDataResultsEvents getEvents() {
        return this.events;
    }

    public void setEvents(HeroesResponseDataResultsEvents events) {
        this.events = events;
    }

    public HeroesResponseDataResultsUrls[] getUrls() {
        return this.urls;
    }

    public void setUrls(HeroesResponseDataResultsUrls[] urls) {
        this.urls = urls;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroesResponseDataResultsComics getComics() {
        return this.comics;
    }

    public void setComics(HeroesResponseDataResultsComics comics) {
        this.comics = comics;
    }

    public String getModified() {
        return this.modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }
}
