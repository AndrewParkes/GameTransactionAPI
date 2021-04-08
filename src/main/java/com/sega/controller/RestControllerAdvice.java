package com.sega.controller;

import com.sega.exception.ProcessingRequestException;
import com.sega.model.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(RestControllerAdvice.class);

    @ExceptionHandler(ProcessingRequestException.class)
    public ResponseEntity<ResponseResult> handleException(ProcessingRequestException e) {

        log.error(e.getMessage());

        ResponseResult rs = new ResponseResult(e.getResponseMessage(), e.getErrorCode());
        return new ResponseEntity<>(rs, e.getHttpStatus());
    }
}
