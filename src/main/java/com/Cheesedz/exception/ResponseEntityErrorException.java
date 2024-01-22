package com.Cheesedz.exception;

import com.Cheesedz.payload.ResponseObject;
import org.springframework.http.ResponseEntity;

public class ResponseEntityErrorException extends RuntimeException {
    private static final long serialVersionUID = -3156815846745801694L;

    private transient ResponseEntity<ResponseObject> apiResponse;

    public ResponseEntityErrorException(ResponseEntity<ResponseObject> apiResponse) {
        this.apiResponse = apiResponse;
    }

    public ResponseEntity<ResponseObject> getApiResponse() {
        return apiResponse;
    }
}