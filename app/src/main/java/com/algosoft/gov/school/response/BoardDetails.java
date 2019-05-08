package com.algosoft.gov.school.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Algosoft on 12/13/2018.
 */

public class BoardDetails {

    @SerializedName("encrypt_id")
    @Expose
    private String encrypt_id;


    @SerializedName("board_name")
    @Expose
    private String board_name;


    @SerializedName("board_short_name")
    @Expose
    private String board_short_name;

    public String getEncrypt_id() {
        return encrypt_id;
    }

    public void setEncrypt_id(String encrypt_id) {
        this.encrypt_id = encrypt_id;
    }

    public String getBoard_name() {
        return board_name;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }

    public String getBoard_short_name() {
        return board_short_name;
    }

    public void setBoard_short_name(String board_short_name) {
        this.board_short_name = board_short_name;
    }
}
