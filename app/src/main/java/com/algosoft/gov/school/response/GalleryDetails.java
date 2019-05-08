package com.algosoft.gov.school.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Algosoft on 1/4/2019.
 */

public class GalleryDetails {

    @SerializedName("galleryId")
    @Expose
    private String galleryId;

    @SerializedName("galleryName")
    @Expose
    private String galleryName;

    @SerializedName("galleryYear")
    @Expose
    private String galleryYear;

    @SerializedName("galleryImage")
    @Expose
    private String galleryImage;

    public String getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(String galleryId) {
        this.galleryId = galleryId;
    }

    public String getGalleryName() {
        return galleryName;
    }

    public void setGalleryName(String galleryName) {
        this.galleryName = galleryName;
    }

    public String getGalleryYear() {
        return galleryYear;
    }

    public void setGalleryYear(String galleryYear) {
        this.galleryYear = galleryYear;
    }

    public String getGalleryImage() {
        return galleryImage;
    }

    public void setGalleryImage(String galleryImage) {
        this.galleryImage = galleryImage;
    }
}
