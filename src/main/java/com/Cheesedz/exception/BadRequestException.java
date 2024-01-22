package com.Cheesedz.exception;

import com.Cheesedz.payload.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private ResponseObject apiResponse;

    public BadRequestException(ResponseObject apiResponse) {
        super();
        this.apiResponse = apiResponse;
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseObject getApiResponse() {
        return apiResponse;
    }
}