package com.algosoft.gov.school.response;

import java.io.Serializable;

/**
 * Created by Algosoft on 1/10/2019.
 */

public class ExaminationDetails implements Serializable{
    public String eventId;
    public String evenntTitle;
    public String eventVenue;
    public String evenntMessage;
    public String eventAbout;
    public String evenntType;
    public String eventStart;
    public String evenntEnd;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEvenntTitle() {
        return evenntTitle;
    }

    public void setEvenntTitle(String evenntTitle) {
        this.evenntTitle = evenntTitle;
    }

    public String getEventVenue() {
        return eventVenue;
    }

    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }

    public String getEvenntMessage() {
        return evenntMessage;
    }

    public void setEvenntMessage(String evenntMessage) {
        this.evenntMessage = evenntMessage;
    }

    public String getEventAbout() {
        return eventAbout;
    }

    public void setEventAbout(String eventAbout) {
        this.eventAbout = eventAbout;
    }

    public String getEvenntType() {
        return evenntType;
    }

    public void setEvenntType(String evenntType) {
        this.evenntType = evenntType;
    }

    public String getEventStart() {
        return eventStart;
    }

    public void setEventStart(String eventStart) {
        this.eventStart = eventStart;
    }

    public String getEvenntEnd() {
        return evenntEnd;
    }

    public void setEvenntEnd(String evenntEnd) {
        this.evenntEnd = evenntEnd;
    }
}
