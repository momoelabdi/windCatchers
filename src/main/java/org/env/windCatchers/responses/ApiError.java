package org.env.windCatchers.responses;

public class ApiError {
    private String message;
    private String errorCode;
    private int status;

    public ApiError() {
    }

    public ApiError(String message, String errorCode, int status) {
        this.message = message;
        this.errorCode = errorCode;
        this.status = status;
    }

    // Getters
    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public int getStatus() {
        return status;
    }

    // Setters
    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
