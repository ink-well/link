package com.inkwell.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Link {
    @Id
    @GeneratedValue
    private Long Id;
    private String title;
    private String url;
    private String category;
    private Integer rate;
    private String createdAt;
    private String updatedAt;

    private Link() {
    }

    public Link(String title, String url, String category, Integer rate) {
        this.title = title;
        this.url = url;
        this.category = category;
        this.rate = rate;
        this.createdAt = new Date().toString();
        this.updatedAt = null;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public void update(Link newLink) {
        this.title = (newLink.title == null) ? this.title : newLink.title;
        this.url = (newLink.url == null) ? this.url : newLink.url;
        this.category = (newLink.category == null) ? this.category : newLink.category;
        this.rate = (newLink.rate == null) ? this.rate : newLink.rate;
    }

    @Override
    public String toString() {
        return String.format("%s - %s, %s", title, url, category);
    }
}
