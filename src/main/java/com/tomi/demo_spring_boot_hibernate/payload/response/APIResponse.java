package com.tomi.demo_spring_boot_hibernate.payload.response;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class APIResponse <T> implements Serializable {

    private String responseMessage;
    private T responseData;
    private boolean success;

    public APIResponse(String responseMessage, T responseData, boolean success) {
        this.responseMessage = responseMessage;
        this.responseData = responseData;
        this.success = success;
    }

    private LocalDateTime timestamp;


    public APIResponse(String responseMessage, T responseData) {
        this.responseMessage = responseMessage;
        this.responseData = responseData;
    }

    public APIResponse(String responseMessage, T responseData, LocalDateTime timestamp) {
        this.responseMessage = responseMessage;
        this.responseData = responseData;
        this.timestamp = timestamp;
    }

    public APIResponse(String responseMessage, T responseData, boolean success, LocalDateTime timestamp) {
        this.responseMessage = responseMessage;
        this.responseData = responseData;
        this.success = success;
        this.timestamp = timestamp;
    }
}
