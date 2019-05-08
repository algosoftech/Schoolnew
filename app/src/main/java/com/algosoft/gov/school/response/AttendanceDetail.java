package com.algosoft.gov.school.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Algosoft on 1/14/2019.
 */

public class AttendanceDetail {
    @SerializedName("studentId")
    @Expose
    public String studentId;

    @SerializedName("studentName")
    @Expose
    public String studentName;

    @SerializedName("studentImage")
    @Expose
    public String studentImage;

    @SerializedName("status")
    @Expose
    public String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
