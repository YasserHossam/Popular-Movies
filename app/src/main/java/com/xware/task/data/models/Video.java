package com.xware.task.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yasser on 10/10/17.
 */

public class Video {
    @SerializedName("id")
    private String id;

    @SerializedName("key")
    private String key;

    @SerializedName("name")
    private String videoName;

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getVideoName() {
        return videoName;
    }
}
