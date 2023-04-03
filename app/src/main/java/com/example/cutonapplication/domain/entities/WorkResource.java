package com.example.cutonapplication.domain.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkResource {
    @SerializedName("route")
    @Expose
    public String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "WorkResource{" +
                "link='" + link + '\'' +
                '}';
    }
}
