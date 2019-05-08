package com.algosoft.gov.school.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Algosoft on 1/4/2019.
 */

public class StudentDetail implements Serializable{

    private static final long serialVersionUID = 1L;

    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public StudentDetail() {
    }

    public StudentDetail(boolean isSelected, String studentId, String studentName, String studentImage) {
        this.isSelected = isSelected;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentImage = studentImage;
    }

    @SerializedName("studentId")
    @Expose
    private String studentId;

    @SerializedName("studentName")
    @Expose
    private String studentName;

    @SerializedName("studentImage")
    @Expose
    private String studentImage;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentImage() {
        return studentImage;
    }

    public void setStudentImage(String studentImage) {
        this.studentImage = studentImage;
    }

    @Override
    public String toString(){
        return studentName;
    }
}
