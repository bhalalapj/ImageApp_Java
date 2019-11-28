package com.bbusiness.app.imageapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sponsorship {

    @SerializedName("impression_urls")
    @Expose
    private List<String> impressionUrls = null;
    @SerializedName("impressions_id")
    @Expose
    private String impressionsId;
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("sponsor")
    @Expose
    private Sponsor sponsor;

    public List<String> getImpressionUrls() {
        return impressionUrls;
    }

    public void setImpressionUrls(List<String> impressionUrls) {
        this.impressionUrls = impressionUrls;
    }

    public String getImpressionsId() {
        return impressionsId;
    }

    public void setImpressionsId(String impressionsId) {
        this.impressionsId = impressionsId;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

}