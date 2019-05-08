package com.algosoft.gov.school.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.algosoft.gov.school.response.BoardDetails;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Algosoft on 12/10/2018.
 */

public class PreferenceUtil {

    static public void setPreferenceObject(Context c, Object modal,String key) {
        /**** storing object in preferences  ****/
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(c.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String jsonObject = gson.toJson(modal);
        prefsEditor.putString(key, jsonObject);
        prefsEditor.commit();

    }
    static public void clearPreferenceObject(Context c) {
        getSharedPreferences(c).edit().clear().apply();
    }

    public static void setBoardList(Context context, String boardlist) {
        getSharedPreferences(context).edit().putString("BoardList", boardlist).apply();
    }

    public static String getBoardList(Context context) {
        return getSharedPreferences(context).getString("BoardList", null);
    }
    public static SharedPreferences getSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setBranchCode(Context context, String branchcode){
        getSharedPreferences(context).edit().putString("branchcode",branchcode).apply();
    }

    public static String getBranchCode(Context context){
        return getSharedPreferences(context).getString("branchcode","");
    }

    public static void setDeviceId(Context context, String devieid){
        getSharedPreferences(context).edit().putString("deviceId",devieid).apply();
    }

    public static String getDeviceId(Context context){
        return getSharedPreferences(context).getString("deviceId","");
    }


    public static void setSchoolId(Context context, String schoolId){
        getSharedPreferences(context).edit().putString("schoolId",schoolId).apply();
    }

    public static String getSchoolId(Context context){
        return getSharedPreferences(context).getString("schoolId","");
    }


    public static void setSchoolName(Context context, String schoolName){
        getSharedPreferences(context).edit().putString("schoolName",schoolName).apply();
    }

    public static String getSchoolName(Context context){
        return getSharedPreferences(context).getString("schoolName","");
    }

    public static void setBranchId(Context context, String BranchId){
        getSharedPreferences(context).edit().putString("BranchId",BranchId).apply();
    }

    public static String getBranchId(Context context){
        return getSharedPreferences(context).getString("BranchId","");
    }

    public static void setBranchName(Context context, String BranchName){
        getSharedPreferences(context).edit().putString("BranchName",BranchName).apply();
    }

    public static String getBranchName(Context context){
        return getSharedPreferences(context).getString("BranchName","");
    }

    public static void setUserType(Context context, String UserType){
        getSharedPreferences(context).edit().putString("UserType",UserType).apply();
    }

    public static String getUserType(Context context){
        return getSharedPreferences(context).getString("UserType","");
    }

    public static void setUserId(Context context, String UserId){
        getSharedPreferences(context).edit().putString("UserId",UserId).apply();
    }

    public static String getUserId(Context context){
        return getSharedPreferences(context).getString("UserId","");
    }

    public static void setEmployeeId(Context context, String EmployeeId){
        getSharedPreferences(context).edit().putString("EmployeeId",EmployeeId).apply();
    }

    public static String getEmployeeId(Context context){
        return getSharedPreferences(context).getString("EmployeeId","");
    }

    public static void setUserFName(Context context, String UserFName){
        getSharedPreferences(context).edit().putString("UserFName",UserFName).apply();
    }

    public static String getUserFName(Context context){
        return getSharedPreferences(context).getString("UserFName","");
    }

    public static void setUserMName(Context context, String UserMName){
        getSharedPreferences(context).edit().putString("UserMName",UserMName).apply();
    }

    public static String getUserMName(Context context){
        return getSharedPreferences(context).getString("UserMName","");
    }

    public static void setUserLName(Context context, String UserLName){
        getSharedPreferences(context).edit().putString("UserLName",UserLName).apply();
    }

    public static String getUserLName(Context context){
        return getSharedPreferences(context).getString("UserLName","");
    }

    public static void setUserEmail(Context context, String UserEmail){
        getSharedPreferences(context).edit().putString("UserEmail",UserEmail).apply();
    }

    public static String getUserEmail(Context context){
        return getSharedPreferences(context).getString("UserEmail","");
    }

    public static void setUserPhone(Context context, String UserPhone){
        getSharedPreferences(context).edit().putString("UserPhone",UserPhone).apply();
    }

    public static String getUserPhone(Context context){
        return getSharedPreferences(context).getString("UserPhone","");
    }

    public static void setUserLastlogin(Context context, String UserLastLogin){
        getSharedPreferences(context).edit().putString("UserLastLogin",UserLastLogin).apply();
    }

    public static String getUserLastlogin(Context context){
        return getSharedPreferences(context).getString("UserLastLogin","");
    }


    public static void setUserProfileAddress(Context context, String UserAddress){
        getSharedPreferences(context).edit().putString("UserAddress",UserAddress).apply();
    }

    public static String getUserProfileAddress(Context context){
        return getSharedPreferences(context).getString("UserAddress","");
    }

    public static void setUserLocality(Context context, String UserLocality){
        getSharedPreferences(context).edit().putString("UserLocality",UserLocality).apply();
    }

    public static String setUserLocality(Context context){
        return getSharedPreferences(context).getString("UserLocality","");
    }

    public static void setUserCity(Context context, String UserCity){
        getSharedPreferences(context).edit().putString("UserCity",UserCity).apply();
    }

    public static String getUserCity(Context context){
        return getSharedPreferences(context).getString("UserCity","");
    }

    public static void setUserState(Context context, String UserState){
        getSharedPreferences(context).edit().putString("UserState",UserState).apply();
    }

    public static String getUserState(Context context){
        return getSharedPreferences(context).getString("UserState","");
    }

    public static void setUserZipcode(Context context, String UserZipcode){
        getSharedPreferences(context).edit().putString("UserZipcode",UserZipcode).apply();
    }

    public static String getUserZipcode(Context context){
        return getSharedPreferences(context).getString("UserZipcode","");
    }


    public static void settype(Context context, String name){
        getSharedPreferences(context).edit().putString("name",name).apply();
    }

    public static String gettype(Context context){
        return getSharedPreferences(context).getString("name","");
    }


    public static String getMenuListFromServer(Context context) {
        return getSharedPreferences(context).getString("menuList", "");
    }

    public static void setMenuListFromServer(Context context, String menuList) {
        getSharedPreferences(context).edit().putString("menuList", menuList).apply();
    }

    public static String getDefaultBoardIdFromServer(Context context) {
        return getSharedPreferences(context).getString("boardId", "");
    }

    public static void setDefaultBoardIdFromServer(Context context, String boardId) {
        getSharedPreferences(context).edit().putString("boardId", boardId).apply();
    }

    public static String getClassListFromServer(Context context) {
        return getSharedPreferences(context).getString("classList", "");
    }

    public static void setClassListFromServer(Context context, String classList) {
        getSharedPreferences(context).edit().putString("classList", classList).apply();
    }


    public static String getOwnClassListFromServer(Context context) {
        return getSharedPreferences(context).getString("ownclassList", "");
    }

    public static void setOwnClassListFromServer(Context context, String ownclassList) {
        getSharedPreferences(context).edit().putString("ownclassList", ownclassList).apply();
    }


    public static String getSectionListFromServer(Context context) {
        return getSharedPreferences(context).getString("sectionList", "");
    }

    public static void setSectionListFromServer(Context context, String sectionList) {
        getSharedPreferences(context).edit().putString("sectionList", sectionList).apply();
    }

    public static String getSubjectListFromServer(Context context) {
        return getSharedPreferences(context).getString("subjectList", "");
    }

    public static void setSubjectListFromServer(Context context, String subjectList) {
        getSharedPreferences(context).edit().putString("subjectList", subjectList).apply();
    }

    public static String getNewsListFromServer(Context context) {
        return getSharedPreferences(context).getString("newsList", "");
    }

    public static void setNewsListFromServer(Context context, String newsList) {
        getSharedPreferences(context).edit().putString("newsList", newsList).apply();
    }

    public static String getGalleryListFromServer(Context context) {
        return getSharedPreferences(context).getString("galleryList", "");
    }

    public static void setGalleryListFromServer(Context context, String galleryList) {
        getSharedPreferences(context).edit().putString("galleryList", galleryList).apply();
    }


    public static String getLeavetypeFromServer(Context context) {
        return getSharedPreferences(context).getString("leavetypelist", "");
    }

    public static void setLeavetypeFromServer(Context context, String leavetypelist) {
        getSharedPreferences(context).edit().putString("leavetypelist", leavetypelist).apply();
    }

    public static String getStudentListFromServer(Context context) {
        return getSharedPreferences(context).getString("studentlist", "");
    }

    public static void setStudentListFromServer(Context context, String studentlist) {
        getSharedPreferences(context).edit().putString("studentlist", studentlist).apply();
    }



    public static String getSelectedBoardFromServer(Context context) {
        return getSharedPreferences(context).getString("selectedboard", "");
    }

    public static void setSelectedBoardFromServer(Context context, String selectedboard) {
        getSharedPreferences(context).edit().putString("selectedboard", selectedboard).apply();
    }

    public static String getSelectedTypeFromServer(Context context) {
        return getSharedPreferences(context).getString("selectedtype", "");
    }

    public static void setSelectedTypeFromServer(Context context, String selectedboard) {
        getSharedPreferences(context).edit().putString("selectedtype", selectedboard).apply();
    }


    public static String getCalenderListFromServer(Context context) {
        return getSharedPreferences(context).getString("calenderList", "");
    }

    public static void setCalenderListFromServer(Context context, String calenderList) {
        getSharedPreferences(context).edit().putString("calenderList", calenderList).apply();
    }

    public static void setFeedbackListFromServer(Context context, String feedbackList) {
        getSharedPreferences(context).edit().putString("feedbackList", feedbackList).apply();
    }


    public static void setReceivedFeedbackListFromServer(Context context, String feedbackList) {
        getSharedPreferences(context).edit().putString("feedbackList", feedbackList).apply();
    }

    public static Boolean getIsTeacherPresent(Context context){
        return getSharedPreferences(context).getBoolean("isTeacherPresent",false);
    }

    public static void setIsTeacherPresent(Context context, Boolean isTeacherPresent){
        getSharedPreferences(context).edit().putBoolean("isTeacherPresent",isTeacherPresent).apply();
    }

    public static String getAttendanceDate(Context context){
        return getSharedPreferences(context).getString("attendanceDate","");
    }
    public static void setAttendanceDate(Context context, String date){
        getSharedPreferences(context).edit().putString("attendanceDate",date).apply();
    }

    public static String getAttendanceTime(Context context){
        return getSharedPreferences(context).getString("attendanceTime","");
    }

    public static void setAttendanceTime(Context context, String time){
        getSharedPreferences(context).edit().putString("attendanceTime",time).apply();
    }


    public static String getHomeworkListFromServer(Context context) {
        return getSharedPreferences(context).getString("studentlist", "");
    }

    public static void setHomeworkListFromServer(Context context, String homeWorkList) {
        getSharedPreferences(context).edit().putString("homeworklist", homeWorkList).apply();
    }
    public static String getTeacherTimeTable(Context context) {
        return getSharedPreferences(context).getString("studentlist", "");
    }

    public static void setTeacherTimeTable(Context context, String homeWorkList) {
        getSharedPreferences(context).edit().putString("homeworklist", homeWorkList).apply();
    }
}
