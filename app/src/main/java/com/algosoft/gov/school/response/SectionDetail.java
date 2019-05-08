package com.algosoft.gov.school.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Algosoft on 1/3/2019.
 */

public class SectionDetail {

    @SerializedName("sectionId")
    @Expose
    private String sectionId;


    @SerializedName("sectionName")
    @Expose
    private String sectionName;

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    @Override
    public String toString(){
        return sectionName;
    }
}
