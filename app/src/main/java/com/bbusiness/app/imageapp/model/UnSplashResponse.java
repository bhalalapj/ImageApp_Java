package com.bbusiness.app.imageapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UnSplashResponse {
    public UnSplashResponse()
    {
        super();
    }

    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}



