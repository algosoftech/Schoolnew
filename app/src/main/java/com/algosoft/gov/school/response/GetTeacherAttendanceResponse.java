package com.algosoft.gov.school.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetTeacherAttendanceResponse {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private Result result;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
    public class Result {

        @SerializedName("attendanceData")
        @Expose
        private List<AttendanceData> attendanceData = null;

        public List<AttendanceData> getAttendanceData() {
            return attendanceData;
        }

        public void setAttendanceData(List<AttendanceData> attendanceData) {
            this.attendanceData = attendanceData;
        }


        public class AttendanceData {

            @SerializedName("attendanceType")
            @Expose
            private String attendanceType;
            @SerializedName("attendanceDate")
            @Expose
            private String attendanceDate;
            @SerializedName("attendanceTime")
            @Expose
            private String attendanceTime;

            public String getAttendanceType() {
                return attendanceType;
            }

            public void setAttendanceType(String attendanceType) {
                this.attendanceType = attendanceType;
            }

            public String getAttendanceDate() {
                return attendanceDate;
            }

            public void setAttendanceDate(String attendanceDate) {
                this.attendanceDate = attendanceDate;
            }

            public String getAttendanceTime() {
                return attendanceTime;
            }

            public void setAttendanceTime(String attendanceTime) {
                this.attendanceTime = attendanceTime;
            }
        }
    }
    }
