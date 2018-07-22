package com.moneytap.assignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Query {

    @SerializedName("pages")
    @Expose
    private List<Page> pages = null;

    /**
     * No args constructor for use in serialization
     */
    public Query() {
    }

    /**
     * @param pages
     */
    public Query(List<Page> pages) {
        super();
        this.pages = pages;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

}