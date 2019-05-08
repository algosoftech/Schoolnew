package com.algosoft.gov.school.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Algosoft on 1/4/2019.
 */

public class ImageDetails {

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
    private ArrayList<GalleryImage> galleryImage;

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

    public ArrayList<GalleryImage> getGalleryImage() {
        return galleryImage;
    }

    public void setGalleryImage(ArrayList<GalleryImage> galleryImage) {
        this.galleryImage = galleryImage;
    }
}
