package com.Cheesedz.payload;

import lombok.Data;

@Data
public class SignupResponse {
    private String message;
    private String status;
    public SignupResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
