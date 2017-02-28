package com.lbjfan.testdemo.model;

import org.json.JSONObject;

/**
 * Created by ${lbjfan} on 17-2-28.
 */
public class CanadaInfo {

//    "title":"Flag",
//            "description":null,
//            "imageHref":"http://images.findicons.com/files/icons/662/world_flag/128/flag_of_canada.png"

    private String title;
    private String description;
    private String imageHref;

    public CanadaInfo() {

    }

    public CanadaInfo(JSONObject jsonObject) {
        title = jsonObject.optString("title");
        description = jsonObject.optString("description");
        imageHref = jsonObject.optString("imageHref");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

    @Override
    public String toString() {
        return "CanadaInfo{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageHref='" + imageHref + '\'' +
                '}';
    }
}
