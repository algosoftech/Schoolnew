package com.algosoft.gov.school.services;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.util.List;


public class WebServicesURL {

    private String baseURL="http://sms.algosoftech.in/api/user/";

    private String teacherbaseURL= "http://sms.algosoftech.in/api/teacher/";
    private String userBranchCodeURL = baseURL+"checkBranchCode";
    private String userLoginURL = baseURL+"userAuthenticate";
    private String deviceIdUpdateURL = baseURL+"updateDeviceId";
    private String profiledataURL = baseURL+"getProfileData";
    private String updateprofiledataURL = baseURL+"updateProfileData";
    private String getlogindataURL = baseURL+"getLoginType";
    private String getmenudataURL = baseURL+"getDashboardData";
    private String getclassListURL = teacherbaseURL+"getClassList";
    private String getsectionListURL = teacherbaseURL+"getSectionList";
    private String getsubjectListURL = teacherbaseURL+"getSubjectList";
    private String getsyllabusListURL = teacherbaseURL+"getSyllabus";

    private String getleavetypeDetailURL = baseURL+"getLeavesTypeList";
    private String getgalleryListURL = baseURL+"getGalleryList";
    private String getgalleryDetailURL = baseURL+"getGalleryDetails";
    private String getnewsListURL = baseURL+"getNewsList";
    private String getnewsDetailURL = baseURL+"getNewsDetails";
    private String getnoticeBoardListURL = baseURL+"getNoticeBoardList";
    private String getnotificationListURL = baseURL+"getNotificationList";

    private String sendleaverequestdataURL = baseURL+"sendLeaveRequest";
    private String calenderdataURL = baseURL+"getCalenderData";
    private String feedbackdataURL = baseURL+"getFeedbackList";
    private String getattendancedataURL = teacherbaseURL+"getStudentAttendance";
    private String savestudentattendanceURL = teacherbaseURL+"setStudentAttendance";
    private String saveteacherattendanceURL = teacherbaseURL+"setOwnAttendance";
    private String getteacherattendanceListURL = teacherbaseURL+"getOwnAttendance";
    private String submitStudentFeedbackURL = baseURL+"sendFeedback";

    private String getstudentListURL = teacherbaseURL+"getStudentList";
    private String getOwnstudentListURL = teacherbaseURL+"getOwnStudentList";
    private String getSavestudentattendanceListURL = baseURL+"getOwnStudentList";
    private String gethomeworkListURL = teacherbaseURL+"getHomeWorkList";
    private String getAssignmentListURL = teacherbaseURL+"getAssignmentList";
    private String getMessageListURL = teacherbaseURL+"getMessageList";
    private String SendMessageToParentsURL=teacherbaseURL+"sendMessageToParent";
    private String TeacherTimeTableURL=teacherbaseURL+"getTimeTableTeacher";



    JSONParser jsonParser=new JSONParser();

    public JSONObject checkSchoolCode( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(userBranchCodeURL,params);
    }

    public JSONObject userLogin(List<NameValuePair> userData) {
        return jsonParser.getJSONFromUrl(userLoginURL,userData);
    }

    public JSONObject deviceIdUpdate( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(deviceIdUpdateURL,params);
    }

    public JSONObject getProfileData( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(profiledataURL,params);
    }

    public JSONObject updateProfileData( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(updateprofiledataURL,params);
    }

    public JSONObject getLoginData( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(getlogindataURL,params);
    }

    public JSONObject getMenuData( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(getmenudataURL,params);
    }

    public JSONObject getClassData( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(getclassListURL,params);
    }

    public JSONObject getSectionData( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(getsectionListURL,params);
    }

    public JSONObject getSubjectData( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(getsubjectListURL,params);
    }
    public JSONObject getSyllabusData( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(getsyllabusListURL,params);
    }


    public JSONObject getGalleryList( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(getgalleryListURL,params);
    }

    public JSONObject getGalleryDetail( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(getgalleryDetailURL,params);
    }

    public JSONObject getNewsList( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(getnewsListURL,params);
    }

    public JSONObject getNewsDetail( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(getnewsDetailURL,params);
    }


    public JSONObject setNoticeList( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(getnoticeBoardListURL,params);
    }

    public JSONObject getNoticeList( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(getnoticeBoardListURL,params);
    }


    public JSONObject setNotificationList( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(getnotificationListURL,params);
    }

    public JSONObject getNotificationList( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(getnotificationListURL,params);
    }

    public JSONObject getLeaveTypeList( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(getleavetypeDetailURL,params);
    }


    public JSONObject getStudentListData( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(getstudentListURL,params);
    }


    public JSONObject getOwnStudentListData( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(getOwnstudentListURL,params);
    }


    public JSONObject submitFeedbackData( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(submitStudentFeedbackURL,params);
    }


    public JSONObject sendLeaveRequestData( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(sendleaverequestdataURL,params);
    }

    public JSONObject getCalenderData( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(calenderdataURL,params);
    }

    public JSONObject getFeedBackData( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(feedbackdataURL,params);
    }


    public JSONObject getAttendanceData( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(getattendancedataURL,params);
    }

    public JSONObject saveStudentAttendance( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(savestudentattendanceURL,params);
    }
    public JSONObject saveTeacherAttendance( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(saveteacherattendanceURL,params);
    }
    public JSONObject getTeacherAttendance( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(getteacherattendanceListURL,params);
    }
    public JSONObject getSavestudentAttendance( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(getSavestudentattendanceListURL,params);
    }


    public JSONObject getHomworkData( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(gethomeworkListURL,params);
    }

    public JSONObject getAssignmentData( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(getAssignmentListURL,params);
    }

    public JSONObject getMessageList( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(getMessageListURL,params);
    }
    public JSONObject SendMessageToParents( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(SendMessageToParentsURL,params);
    }
    public JSONObject GetTeacherTimeTable( List<NameValuePair> params) {
        return jsonParser.getJSONFromUrl(TeacherTimeTableURL,params);
    }



















}