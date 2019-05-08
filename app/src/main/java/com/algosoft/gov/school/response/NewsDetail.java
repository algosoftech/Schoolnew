package com.algosoft.gov.school.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Algosoft on 1/3/2019.
 */

public class NewsDetail {

    @SerializedName("newsId")
    @Expose
    private String newsId;

    @SerializedName("newsTitle")
    @Expose
    private String newsTitle;

    @SerializedName("newsYear")
    @Expose
    private String newsYear;

    @SerializedName("newsImage")
    @Expose
    private String newsImage;

    @SerializedName("newsContent")
    @Expose
    private String newsContent;

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsYear() {
        return newsYear;
    }

    public void setNewsYear(String newsYear) {
        this.newsYear = newsYear;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }
}
