package com.example.crud_new.response;


public class ApiResponse {
    private String message;

    public ApiResponse(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public ApiResponse(boolean success, String message, Object data) {
        this.message = message;
    }

}
