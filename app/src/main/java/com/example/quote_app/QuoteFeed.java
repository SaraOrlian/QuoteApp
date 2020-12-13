package com.example.quote_app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuoteFeed {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("length")
    @Expose
    private Integer length;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

}