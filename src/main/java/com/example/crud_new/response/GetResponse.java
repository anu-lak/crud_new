package com.example.crud_new.response;

import java.util.List;

import com.example.crud_new.model.Manager;

public class GetResponse {
    private String message;
    private List<Manager> details;

    public GetResponse(String message, List<Manager> details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Manager> getDetails() {
        return details;
    }

    public void setDetails(List<Manager> details) {
        this.details = details;
    }

}
