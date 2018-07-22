package com.moneytap.assignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Page {

    @SerializedName("pageid")
    @Expose
    private Integer pageid;
    @SerializedName("ns")
    @Expose
    private Integer ns;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    private String desc;
    @SerializedName("index")
    @Expose
    private Integer index;
    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;

    /**
     * No args constructor for use in serialization
     */
    public Page() {
    }

    /**
     * @param index
     * @param title
     * @param ns
     * @param thumbnail
     * @param pageid
     */
    public Page(Integer pageid, Integer ns, String title, Integer index, Thumbnail thumbnail) {
        super();
        this.pageid = pageid;
        this.ns = ns;
        this.title = title;
        this.index = index;
        this.thumbnail = thumbnail;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getPageid() {
        return pageid;
    }

    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    public Integer getNs() {
        return ns;
    }

    public void setNs(Integer ns) {
        this.ns = ns;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

}