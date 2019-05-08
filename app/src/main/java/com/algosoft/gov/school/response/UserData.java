package com.algosoft.gov.school.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Algosoft on 12/10/2018.
 */

public class UserData {
    @SerializedName("schoolId")
    @Expose
    private String schoolId;

    @SerializedName("schoolName")
    @Expose
    private String schoolName;

    @SerializedName("branchId")
    @Expose
    private String branchId;

    @SerializedName("branchCode")
    @Expose
    private String branchCode;

    @SerializedName("branchName")
    @Expose
    private String branchName;

    @SerializedName("userType")
    @Expose
    private String userType;

    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("EmployeeId")
    @Expose
    private String EmployeeId;

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

    @SerializedName("userLastLogin")
    @Expose
    private String userLastLogin;

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


    @SerializedName("boardId")
    @Expose
    private String boardId;

    @SerializedName("boardName")
    @Expose
    private String boardName;



    @SerializedName("childId")
    @Expose
    private String childId;

    @SerializedName("childName")
    @Expose
    private String childName;

    @SerializedName("childClassId")
    @Expose
    private String childClassId;

    @SerializedName("childClass")
    @Expose
    private String childClass;

    @SerializedName("childSectionId")
    @Expose
    private String childSectionId;

    @SerializedName("childSection")
    @Expose
    private String childSection;

    @SerializedName("childList")
    @Expose
    private ArrayList<ChildDetails> childList;

    @SerializedName("boardList")
    @Expose
    private ArrayList<BoardDetails> boardList;

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildClassId() {
        return childClassId;
    }

    public void setChildClassId(String childClassId) {
        this.childClassId = childClassId;
    }

    public String getChildClass() {
        return childClass;
    }

    public void setChildClass(String childClass) {
        this.childClass = childClass;
    }

    public String getChildSectionId() {
        return childSectionId;
    }

    public void setChildSectionId(String childSectionId) {
        this.childSectionId = childSectionId;
    }

    public String getChildSection() {
        return childSection;
    }

    public void setChildSection(String childSection) {
        this.childSection = childSection;
    }

    public ArrayList<ChildDetails> getChildList() {
        return childList;
    }

    public void setChildList(ArrayList<ChildDetails> childList) {
        this.childList = childList;
    }

    public ArrayList<BoardDetails> getBoardList() {
        return boardList;
    }

    public void setBoardList(ArrayList<BoardDetails> boardList) {
        this.boardList = boardList;
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

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
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

    public String getUserLastLogin() {
        return userLastLogin;
    }

    public void setUserLastLogin(String userLastLogin) {
        this.userLastLogin = userLastLogin;
    }
}
