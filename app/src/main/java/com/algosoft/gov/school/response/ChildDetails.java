package com.algosoft.gov.school.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Algosoft on 12/13/2018.
 */

public class ChildDetails {

    @SerializedName("student_qunique_id")
    @Expose
    private String student_qunique_id;

    @SerializedName("student_f_name")
    @Expose
    private String student_f_name;

    @SerializedName("student_m_name")
    @Expose
    private String student_m_name;

    @SerializedName("student_l_name")
    @Expose
    private String student_l_name;

    @SerializedName("class_id")
    @Expose
    private String class_id;

    @SerializedName("class_name")
    @Expose
    private String class_name;

    @SerializedName("section_id")
    @Expose
    private String section_id;

    @SerializedName("section_name")
    @Expose
    private String section_name;

    public String getStudent_qunique_id() {
        return student_qunique_id;
    }

    public void setStudent_qunique_id(String student_qunique_id) {
        this.student_qunique_id = student_qunique_id;
    }

    public String getStudent_f_name() {
        return student_f_name;
    }

    public void setStudent_f_name(String student_f_name) {
        this.student_f_name = student_f_name;
    }

    public String getStudent_m_name() {
        return student_m_name;
    }

    public void setStudent_m_name(String student_m_name) {
        this.student_m_name = student_m_name;
    }

    public String getStudent_l_name() {
        return student_l_name;
    }

    public void setStudent_l_name(String student_l_name) {
        this.student_l_name = student_l_name;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }
}
