package com.algosoft.gov.school.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NoticeBoardList {
    @SerializedName("noticeBoardId")
    @Expose
    private String noticeBoardId;
    @SerializedName("noticeBoardMessage")
    @Expose
    private String noticeBoardMessage;
    @SerializedName("noticeBoardImage")
    @Expose
    private String noticeBoardImage;
    @SerializedName("noticeBoardDate")
    @Expose
    private String noticeBoardDate;

    public String getNoticeBoardId() {
        return noticeBoardId;
    }

    public void setNoticeBoardId(String noticeBoardId) {
        this.noticeBoardId = noticeBoardId;
    }

    public String getNoticeBoardMessage() {
        return noticeBoardMessage;
    }

    public void setNoticeBoardMessage(String noticeBoardMessage) {
        this.noticeBoardMessage = noticeBoardMessage;
    }

    public String getNoticeBoardImage() {
        return noticeBoardImage;
    }

    public void setNoticeBoardImage(String noticeBoardImage) {
        this.noticeBoardImage = noticeBoardImage;
    }

    public String getNoticeBoardDate() {
        return noticeBoardDate;
    }

    public void setNoticeBoardDate(String noticeBoardDate) {
        this.noticeBoardDate = noticeBoardDate;
    }
}
