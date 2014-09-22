package com.example.hariskljajic.picassooptimizedadapter.Models;

/**
 * Created by Haris Kljajic on 2014-09-12.
 */
public class GalleryFrame {

    private String url;
    private String name;
    private String description;

    public GalleryFrame(String url, String name, String description){
        this.url = url;
        this.name = name;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
