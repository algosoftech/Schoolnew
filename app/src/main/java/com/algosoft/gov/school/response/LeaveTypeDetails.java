package com.algosoft.gov.school.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Algosoft on 1/4/2019.
 */

public class LeaveTypeDetails {

    @SerializedName("leavesTypeId")
    @Expose
    private String leavesTypeId;

    @SerializedName("leavesType")
    @Expose
    private String leavesType;


    @SerializedName("userType")
    @Expose
    private String userType;

    public String getLeavesTypeId() {
        return leavesTypeId;
    }

    public void setLeavesTypeId(String leavesTypeId) {
        this.leavesTypeId = leavesTypeId;
    }

    public String getLeavesType() {
        return leavesType;
    }

    public void setLeavesType(String leavesType) {
        this.leavesType = leavesType;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString(){
        return leavesType;
    }
}
