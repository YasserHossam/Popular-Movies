package com.xware.task.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yasser on 11/10/17.
 */

public class Genre {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
