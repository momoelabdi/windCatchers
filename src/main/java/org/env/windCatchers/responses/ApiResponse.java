package org.env.windCatchers.responses;

import java.time.LocalDateTime;

public class ApiResponse<T> {
    private boolean success;
    private T data;
    private ApiError error;
    private LocalDateTime timestamp;
    private String path;       // Request URI path
    private String method;     // HTTP Method 
    private String traceId;    // trace/correlation id

    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this.success = true;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse(ApiError error) {
        this.success = false;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and setters

    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public ApiError getError() {
        return error;
    }
    public void setError(ApiError error) {
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }

    public String getTraceId() {
        return traceId;
    }
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
}
