package com.moneytap.assignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Page {

    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("description")
    String desc;
    @SerializedName("index")
    @Expose
    Integer index;
    @SerializedName("thumbnail")
    @Expose
    Thumbnail thumbnail;

    /**
     * No args constructor for use in serialization
     */
    Page() {
    }

    public String getDesc() {
        return desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

}