package com.algosoft.gov.school.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeWorkList {

    @SerializedName("classId")
    @Expose
    private String classId;
    @SerializedName("className")
    @Expose
    private String className;
    @SerializedName("sectionId")
    @Expose
    private String sectionId;
    @SerializedName("sectionName")
    @Expose
    private String sectionName;
    @SerializedName("subjectId")
    @Expose
    private String subjectId;
    @SerializedName("subjectName")
    @Expose
    private String subjectName;
    @SerializedName("homeWorkId")
    @Expose
    private String homeWorkId;
    @SerializedName("homeWorkTitle")
    @Expose
    private String homeWorkTitle;
    @SerializedName("homeWorkContent")
    @Expose
    private String homeWorkContent;
    @SerializedName("homeWorkFile")
    @Expose
    private String homeWorkFile;
    @SerializedName("homeWorkFromDate")
    @Expose
    private String homeWorkFromDate;
    @SerializedName("homeWorkToDate")
    @Expose
    private String homeWorkToDate;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

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

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getHomeWorkId() {
        return homeWorkId;
    }

    public void setHomeWorkId(String homeWorkId) {
        this.homeWorkId = homeWorkId;
    }

    public String getHomeWorkTitle() {
        return homeWorkTitle;
    }

    public void setHomeWorkTitle(String homeWorkTitle) {
        this.homeWorkTitle = homeWorkTitle;
    }

    public String getHomeWorkContent() {
        return homeWorkContent;
    }

    public void setHomeWorkContent(String homeWorkContent) {
        this.homeWorkContent = homeWorkContent;
    }

    public String getHomeWorkFile() {
        return homeWorkFile;
    }

    public void setHomeWorkFile(String homeWorkFile) {
        this.homeWorkFile = homeWorkFile;
    }

    public String getHomeWorkFromDate() {
        return homeWorkFromDate;
    }

    public void setHomeWorkFromDate(String homeWorkFromDate) {
        this.homeWorkFromDate = homeWorkFromDate;
    }

    public String getHomeWorkToDate() {
        return homeWorkToDate;
    }

    public void setHomeWorkToDate(String homeWorkToDate) {
        this.homeWorkToDate = homeWorkToDate;
    }

}
