package com.algosoft.gov.school.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageList {

    @SerializedName("messageId")
    @Expose
    private String messageId;
    @SerializedName("messageContent")
    @Expose
    private String messageContent;
    @SerializedName("messageFrom")
    @Expose
    private String messageFrom;
    @SerializedName("messageTo")
    @Expose
    private String messageTo;
    @SerializedName("messageDate")
    @Expose
    private String messageDate;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageFrom() {
        return messageFrom;
    }

    public void setMessageFrom(String messageFrom) {
        this.messageFrom = messageFrom;
    }

    public String getMessageTo() {
        return messageTo;
    }

    public void setMessageTo(String messageTo) {
        this.messageTo = messageTo;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

}
