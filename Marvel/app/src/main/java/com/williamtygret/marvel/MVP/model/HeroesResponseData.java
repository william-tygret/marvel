package com.williamtygret.marvel.MVP.model;

public class HeroesResponseData {
    private int limit;
    private int total;
    private int count;
    private HeroesResponseDataResults[] results;
    private int offset;

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public HeroesResponseDataResults[] getResults() {
        return this.results;
    }

    public void setResults(HeroesResponseDataResults[] results) {
        this.results = results;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
