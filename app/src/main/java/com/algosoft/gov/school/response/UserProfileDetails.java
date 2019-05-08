package com.algosoft.gov.school.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Algosoft on 12/10/2018.
 */

public class UserProfileDetails {
    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("userFName")
    @Expose
    private String userFName;

    @SerializedName("userMName")
    @Expose
    private String userMName;

    @SerializedName("userLName")
    @Expose
    private String userLName;

    @SerializedName("userEmail")
    @Expose
    private String userEmail;

    @SerializedName("userPhone")
    @Expose
    private String userPhone;

    @SerializedName("userAddress")
    @Expose
    private String userAddress;

    @SerializedName("userLocality")
    @Expose
    private String userLocality;

    @SerializedName("userCity")
    @Expose
    private String userCity;

    @SerializedName("userState")
    @Expose
    private String userState;

    @SerializedName("userZipcode")
    @Expose
    private String userZipcode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserFName() {
        return userFName;
    }

    public void setUserFName(String userFName) {
        this.userFName = userFName;
    }

    public String getUserMName() {
        return userMName;
    }

    public void setUserMName(String userMName) {
        this.userMName = userMName;
    }

    public String getUserLName() {
        return userLName;
    }

    public void setUserLName(String userLName) {
        this.userLName = userLName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserLocality() {
        return userLocality;
    }

    public void setUserLocality(String userLocality) {
        this.userLocality = userLocality;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserZipcode() {
        return userZipcode;
    }

    public void setUserZipcode(String userZipcode) {
        this.userZipcode = userZipcode;
    }
}
