package com.example.demo.dto;

public class MessageDto {

    private String message;
    private Boolean success;


    public MessageDto() {
    }

    public MessageDto(String message) {
        this.message = message;
    }

    public MessageDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
