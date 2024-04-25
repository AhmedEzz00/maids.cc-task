package com.example.library_management_system.Error.errorEntity;

import java.time.LocalDateTime;
import java.util.List;

public class ApiResponseError {
    private boolean success;
    private String message;
    private List<String> details;
    private LocalDateTime dateTime;

    public ApiResponseError() {
    }

    public ApiResponseError(String message, List<String> details) {
        this.message = message;
        this.details = details;
        this.success= Boolean.FALSE;
        this.dateTime = LocalDateTime.now();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
