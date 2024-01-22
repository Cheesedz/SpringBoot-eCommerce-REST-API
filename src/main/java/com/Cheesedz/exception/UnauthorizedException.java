package com.Cheesedz.exception;

import com.Cheesedz.payload.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private ResponseObject apiResponse;
    private String message;

    public UnauthorizedException(ResponseObject apiResponse) {
        super();
        this.apiResponse = apiResponse;
    }

    public UnauthorizedException(String message) {
        super(message);
        this.message = message;
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseObject getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(ResponseObject apiResponse) {
        this.apiResponse = apiResponse;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
