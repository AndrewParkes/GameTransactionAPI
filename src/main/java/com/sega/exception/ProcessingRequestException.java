package com.sega.exception;

import org.springframework.http.HttpStatus;

public class ProcessingRequestException extends RuntimeException {

    private HttpStatus httpStatus;
    private int errorCode;
    private String responseMessage;

    public ProcessingRequestException(HttpStatus httpStatus, int errorCode, String responseMessage) {
        super(responseMessage);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.responseMessage = responseMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
