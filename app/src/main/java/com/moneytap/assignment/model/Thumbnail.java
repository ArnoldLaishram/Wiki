package com.moneytap.assignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Thumbnail {

    @SerializedName("source")
    @Expose
    String source;

    /**
     * No args constructor for use in serialization
     */
    Thumbnail() {
    }

    public String getSource() {
        return source;
    }

}