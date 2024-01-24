package com.Cheesedz.payload;

import lombok.Data;

@Data
public class LoginResponse {
    private String message;
    private Object token;

    public LoginResponse(String message, Object token) {
        this.message = message;
        this.token = token;
    }
}
