package com.algosoft.gov.school.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Algosoft on 1/14/2019.
 */

public class FeedBackListDetail {

    @SerializedName("feedbackId")
    @Expose
    public String feedbackId;

    @SerializedName("feedbackFrom")
    @Expose
    public String feedbackFrom;

    @SerializedName("feedbackTO")
    @Expose
    public String feedbackTO;

    @SerializedName("feedbackClass")
    @Expose
    public String feedbackClass;

    @SerializedName("feedbackSection")
    @Expose
    public String feedbackSection;

    @SerializedName("feedbackSubject")
    @Expose
    public String feedbackSubject;

    @SerializedName("feedbackMessage")
    @Expose
    public String feedbackMessage;

    @SerializedName("wayOfTeaching")
    @Expose
    public String wayOfTeaching;

    @SerializedName("knowledgeAboutSubject")
    @Expose
    public String knowledgeAboutSubject;

    @SerializedName("ClassCoOrdination")
    @Expose
    public String ClassCoOrdination;

    @SerializedName("KnowledgeSharing")
    @Expose
    public String KnowledgeSharing;

    @SerializedName("messageDate")
    @Expose
    public String messageDate;

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedbackFrom() {
        return feedbackFrom;
    }

    public void setFeedbackFrom(String feedbackFrom) {
        this.feedbackFrom = feedbackFrom;
    }

    public String getFeedbackTO() {
        return feedbackTO;
    }

    public void setFeedbackTO(String feedbackTO) {
        this.feedbackTO = feedbackTO;
    }

    public String getFeedbackClass() {
        return feedbackClass;
    }

    public void setFeedbackClass(String feedbackClass) {
        this.feedbackClass = feedbackClass;
    }

    public String getFeedbackSection() {
        return feedbackSection;
    }

    public void setFeedbackSection(String feedbackSection) {
        this.feedbackSection = feedbackSection;
    }

    public String getFeedbackSubject() {
        return feedbackSubject;
    }

    public void setFeedbackSubject(String feedbackSubject) {
        this.feedbackSubject = feedbackSubject;
    }

    public String getFeedbackMessage() {
        return feedbackMessage;
    }

    public void setFeedbackMessage(String feedbackMessage) {
        this.feedbackMessage = feedbackMessage;
    }

    public String getWayOfTeaching() {
        return wayOfTeaching;
    }

    public void setWayOfTeaching(String wayOfTeaching) {
        this.wayOfTeaching = wayOfTeaching;
    }

    public String getKnowledgeAboutSubject() {
        return knowledgeAboutSubject;
    }

    public void setKnowledgeAboutSubject(String knowledgeAboutSubject) {
        this.knowledgeAboutSubject = knowledgeAboutSubject;
    }

    public String getClassCoOrdination() {
        return ClassCoOrdination;
    }

    public void setClassCoOrdination(String classCoOrdination) {
        ClassCoOrdination = classCoOrdination;
    }

    public String getKnowledgeSharing() {
        return KnowledgeSharing;
    }

    public void setKnowledgeSharing(String knowledgeSharing) {
        KnowledgeSharing = knowledgeSharing;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }
}
