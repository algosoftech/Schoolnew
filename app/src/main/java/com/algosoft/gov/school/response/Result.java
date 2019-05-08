package com.algosoft.gov.school.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Algosoft on 12/10/2018.
 */

public class Result {
    @SerializedName("branchCode")
    @Expose
    private String branchCode;

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    @SerializedName("userData")
    @Expose
    private UserData userData;

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    @SerializedName("galleryId")
    @Expose
    private String galleryId;

    @SerializedName("galleryName")
    @Expose
    private String galleryName;

    @SerializedName("galleryYear")
    @Expose
    private String galleryYear;

    @SerializedName("galleryImage")
    @Expose
    private ArrayList<GalleryImage> galleryImage;

    public ArrayList<GalleryImage> getGalleryImage() {
        return galleryImage;
    }

    public void setGalleryImage(ArrayList<GalleryImage> galleryImage) {
        this.galleryImage = galleryImage;
    }

    public String getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(String galleryId) {
        this.galleryId = galleryId;
    }

    public String getGalleryName() {
        return galleryName;
    }

    public void setGalleryName(String galleryName) {
        this.galleryName = galleryName;
    }

    public String getGalleryYear() {
        return galleryYear;
    }

    public void setGalleryYear(String galleryYear) {
        this.galleryYear = galleryYear;
    }



    @SerializedName("loginTypeList")
    @Expose
    private ArrayList<LogintypeDetail> logintypeDetailArrayList;

    public ArrayList<LogintypeDetail> getLogintypeDetailArrayList() {
        return logintypeDetailArrayList;
    }

    public void setLogintypeDetailArrayList(ArrayList<LogintypeDetail> logintypeDetailArrayList) {
        this.logintypeDetailArrayList = logintypeDetailArrayList;
    }


    @SerializedName("menuList")
    @Expose
    private ArrayList<MenuList> menuListArrayList;

    public ArrayList<MenuList> getMenuListArrayList() {
        return menuListArrayList;
    }

    public void setMenuListArrayList(ArrayList<MenuList> menuListArrayList) {
        this.menuListArrayList = menuListArrayList;
    }

    @SerializedName("classList")
    @Expose
    private ArrayList<ClassDetail> classDetailArrayList;

    public ArrayList<ClassDetail> getClassDetailArrayList() {
        return classDetailArrayList;
    }

    public void setClassDetailArrayList(ArrayList<ClassDetail> classDetailArrayList) {
        this.classDetailArrayList = classDetailArrayList;
    }

    @SerializedName("sectionList")
    @Expose
    private ArrayList<SectionDetail> sectionDetailArrayList;

    public ArrayList<SectionDetail> getSectionDetailArrayList() {
        return sectionDetailArrayList;
    }

    public void setSectionDetailArrayList(ArrayList<SectionDetail> sectionDetailArrayList) {
        this.sectionDetailArrayList = sectionDetailArrayList;
    }

    @SerializedName("subjectList")
    @Expose
    private ArrayList<SubjectDetail> subjectDetailArrayList;

    public ArrayList<SubjectDetail> getSubjectDetailArrayList() {
        return subjectDetailArrayList;
    }

    public void setSubjectDetailArrayList(ArrayList<SubjectDetail> subjectDetailArrayList) {
        this.subjectDetailArrayList = subjectDetailArrayList;
    }

    @SerializedName("newsList")
    @Expose
    private ArrayList<NewsDetail> newsDetailArrayList;

    public ArrayList<NewsDetail> getNewsDetailArrayList() {
        return newsDetailArrayList;
    }

    public void setNewsDetailArrayList(ArrayList<NewsDetail> newsDetailArrayList) {
        this.newsDetailArrayList = newsDetailArrayList;
    }

    @SerializedName("newsData")
    @Expose
    private NewsDetail newsdata;

    public NewsDetail getNewsdata() {
        return newsdata;
    }

    public void setNewsdata(NewsDetail newsdata) {
        this.newsdata = newsdata;
    }

    @SerializedName("galleryList")
    @Expose
    private ArrayList<GalleryDetails> galleryDetailsArrayList;

    public ArrayList<GalleryDetails> getGalleryDetailsArrayList() {
        return galleryDetailsArrayList;
    }

    public void setGalleryDetailsArrayList(ArrayList<GalleryDetails> galleryDetailsArrayList) {
        this.galleryDetailsArrayList = galleryDetailsArrayList;
    }

    @SerializedName("leavesTypeList")
    @Expose
    private ArrayList<LeaveTypeDetails> leaveTypeDetailsArrayList;

    public ArrayList<LeaveTypeDetails> getLeaveTypeDetailsArrayList() {
        return leaveTypeDetailsArrayList;
    }

    public void setLeaveTypeDetailsArrayList(ArrayList<LeaveTypeDetails> leaveTypeDetailsArrayList) {
        this.leaveTypeDetailsArrayList = leaveTypeDetailsArrayList;
    }


    @SerializedName("studentList")
    @Expose
    private ArrayList<StudentDetail> studentDetailArrayList;

    public ArrayList<StudentDetail> getStudentDetailArrayList() {
        return studentDetailArrayList;
    }

    public void setStudentDetailArrayList(ArrayList<StudentDetail> studentDetailArrayList) {
        this.studentDetailArrayList = studentDetailArrayList;
    }


    @SerializedName("classId")
    @Expose
    private String classId;

    @SerializedName("sectionId")
    @Expose
    private String sectionId;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }


    @SerializedName("eventData")
    @Expose
    private ArrayList<EventDataList> eventDataListArrayList;

    public ArrayList<EventDataList> getEventDataListArrayList() {
        return eventDataListArrayList;
    }

    public void setEventDataListArrayList(ArrayList<EventDataList> eventDataListArrayList) {
        this.eventDataListArrayList = eventDataListArrayList;
    }

    @SerializedName("feedbackId")
    @Expose
    private String feedbackId;

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }


    @SerializedName("feedbackList")
    @Expose
    private ArrayList<FeedBackListDetail> feedBackListDetailArrayList;

    public ArrayList<FeedBackListDetail> getFeedBackListDetailArrayList() {
        return feedBackListDetailArrayList;
    }

    public void setFeedBackListDetailArrayList(ArrayList<FeedBackListDetail> feedBackListDetailArrayList) {
        this.feedBackListDetailArrayList = feedBackListDetailArrayList;
    }

    @SerializedName("attendanceData")
    @Expose
    private ArrayList<AttendanceDetail> attendanceDetailArrayList;

    public ArrayList<AttendanceDetail> getAttendanceDetailArrayList() {
        return attendanceDetailArrayList;
    }

    public void setAttendanceDetailArrayList(ArrayList<AttendanceDetail> attendanceDetailArrayList) {
        this.attendanceDetailArrayList = attendanceDetailArrayList;
    }

    @SerializedName("homeWorkList")
    @Expose
    private ArrayList<HomeWorkList> homeWorkList = null;

    public ArrayList<HomeWorkList> getHomeWorkList() {
        return homeWorkList;
    }

    public void setHomeWorkList(ArrayList<HomeWorkList> homeWorkList) {
        this.homeWorkList = homeWorkList;
    }

    @Expose
    private ArrayList<AssignmentList> assignmentList = null;

    public ArrayList<AssignmentList> getAssignmentList() {
        return assignmentList;
    }

    public void setAssignmentList(ArrayList<AssignmentList> assignmentList) {
        this.assignmentList = assignmentList;
    }


    @SerializedName("messageList")
    @Expose
    private ArrayList<MessageList> messageList = null;

    public ArrayList<MessageList> getMessageList() {
        return messageList;
    }

    public void setMessageList(ArrayList<MessageList> messageList) {
        this.messageList = messageList;
    }

    @SerializedName("notificationList")
    @Expose
    private ArrayList<NotificationList> notificationList = null;

    public ArrayList<NotificationList> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(ArrayList<NotificationList> notificationList) {
        this.notificationList = notificationList;
    }

    @SerializedName("noticeBoardList")
    @Expose
    private ArrayList<NoticeBoardList> noticeBoardList = null;

    public ArrayList<NoticeBoardList> getNoticeBoardList() {
        return noticeBoardList;
    }

    public void setNoticeBoardList(ArrayList<NoticeBoardList> noticeBoardList) {
        this.noticeBoardList = noticeBoardList;
    }

    @SerializedName("TeacherTimeTableList")
    @Expose
    private ArrayList<TimeTable> teacherTimeTableList = null;

    public ArrayList<TimeTable> getTeacherTimeTableList() {
        return teacherTimeTableList;
    }

    public void setTeacherTimeTableList(ArrayList<TimeTable> teacherTimeTableList) {
        this.teacherTimeTableList = teacherTimeTableList;
    }



}
