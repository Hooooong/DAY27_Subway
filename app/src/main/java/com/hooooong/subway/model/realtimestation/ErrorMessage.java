package com.hooooong.subway.model.realtimestation;

/**
 * Created by Android Hong on 2017-10-21.
 */

public class ErrorMessage {
    private int total;
    private String message;
    private int status;
    private String developerMessage;
    private String link;
    private String code;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ClassPojo [total = " + total + ", message = " + message + ", status = " + status + ", developerMessage = " + developerMessage + ", link = " + link + ", code = " + code + "]";
    }
}