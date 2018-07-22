package com.moneytap.assignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parse {

    @SerializedName("title")
    private String title;
    @SerializedName("page")
    private String page;
    @SerializedName("text")
    private Text text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getText() {
        return text.getDescHtml();
    }

    class Text {
        @SerializedName("*")
        private String descHtml;

        public Text(String descHtml) {
            this.descHtml = descHtml;
        }

        public String getDescHtml() {
            return descHtml;
        }

        public void setDescHtml(String descHtml) {
            this.descHtml = descHtml;
        }
    }
}
