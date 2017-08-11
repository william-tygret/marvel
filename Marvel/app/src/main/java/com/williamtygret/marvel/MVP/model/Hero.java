package com.williamtygret.marvel.MVP.model;

import org.json.JSONArray;
import org.json.JSONObject;



/**
 * Created by williamtygret on 8/5/17.
 */
public class Hero {

    private String id;
    private String name;
    private String description;
    private String thumbnail;
    private String resourceURI;


    public String getId() {
        return id;
    }

    public void setId(String id) {
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


}
