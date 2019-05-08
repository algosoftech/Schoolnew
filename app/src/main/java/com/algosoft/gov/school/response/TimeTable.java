package com.algosoft.gov.school.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeTable {
    @SerializedName("period_subject_teacher_id")
    @Expose
    private String periodSubjectTeacherId;
    @SerializedName("working_day_name")
    @Expose
    private String workingDayName;
    @SerializedName("subject_name")
    @Expose
    private String subjectName;
    @SerializedName("class_name")
    @Expose
    private String className;
    @SerializedName("class_section_name")
    @Expose
    private String classSectionName;
    @SerializedName("class_period_name")
    @Expose
    private String classPeriodName;

    public String getPeriodSubjectTeacherId() {
        return periodSubjectTeacherId;
    }

    public void setPeriodSubjectTeacherId(String periodSubjectTeacherId) {
        this.periodSubjectTeacherId = periodSubjectTeacherId;
    }

    public String getWorkingDayName() {
        return workingDayName;
    }

    public void setWorkingDayName(String workingDayName) {
        this.workingDayName = workingDayName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    public String getClassSectionName() {
        return classSectionName;
    }

    public void setClassSectionName(String classSectionName) {
        this.classSectionName = classSectionName;
    }

    public String getClassPeriodName() {
        return classPeriodName;
    }

    public void setClassPeriodName(String classPeriodName) {
        this.classPeriodName = classPeriodName;
    }

}
