package com.moneytap.assignment.model;

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

    }
}
