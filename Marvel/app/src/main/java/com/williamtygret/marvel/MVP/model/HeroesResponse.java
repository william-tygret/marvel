package com.williamtygret.marvel.MVP.model;

public class HeroesResponse {
    private String attributionText;
    private String etag;
    private String status;
    private HeroesResponseData data;
    private int code;
    private String copyright;
    private String attributionHTML;

    public String getAttributionText() {
        return this.attributionText;
    }

    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    public String getEtag() {
        return this.etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HeroesResponseData getData() {
        return this.data;
    }

    public void setData(HeroesResponseData data) {
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCopyright() {
        return this.copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getAttributionHTML() {
        return this.attributionHTML;
    }

    public void setAttributionHTML(String attributionHTML) {
        this.attributionHTML = attributionHTML;
    }
}
