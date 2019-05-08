package com.algosoft.gov.school.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Algosoft on 1/9/2019.
 */

public class EventDataList {

    @SerializedName("eventId")
    @Expose
    private String eventId;

    @SerializedName("eventTitle")
    @Expose
    private String eventTitle;

    @SerializedName("eventVenue")
    @Expose
    private String eventVenue;

    @SerializedName("eventMessage")
    @Expose
    private String eventMessage;

    @SerializedName("eventAbout")
    @Expose
    private String eventAbout;

    @SerializedName("eventType")
    @Expose
    private String eventType;

    @SerializedName("eventStart")
    @Expose
    private String eventStart;

    @SerializedName("eventEnd")
    @Expose
    private String eventEnd;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventVenue() {
        return eventVenue;
    }

    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }

    public String getEventMessage() {
        return eventMessage;
    }

    public void setEventMessage(String eventMessage) {
        this.eventMessage = eventMessage;
    }

    public String getEventAbout() {
        return eventAbout;
    }

    public void setEventAbout(String eventAbout) {
        this.eventAbout = eventAbout;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventStart() {
        return eventStart;
    }

    public void setEventStart(String eventStart) {
        this.eventStart = eventStart;
    }

    public String getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(String eventEnd) {
        this.eventEnd = eventEnd;
    }
}
