package com.smbr.api.infra.exceptions;

import org.springframework.http.HttpStatus;

public class RestErrorMessage {
    private HttpStatus status;
    private String message;

    public RestErrorMessage(HttpStatus httpStatus, String message) {
        this.status = httpStatus;
        this.message = message;
    }
}
