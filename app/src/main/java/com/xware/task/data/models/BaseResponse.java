package com.xware.task.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by yasser on 09/10/17.
 */

public class BaseResponse<T> {
    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    ArrayList<T> results;

    public ArrayList<T> getResults() {
        return results;
    }

    public int getPage() {
        return page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
