package com.williamtygret.marvel.MVP.model;

public class HeroesResponseDataResultsStoriesItems {
    private String resourceURI;
    private String name;
    private String type;

    public String getResourceURI() {
        return this.resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
