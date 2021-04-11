package com.sega.controller;

import com.sega.exception.ProcessingRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class RestErrorController implements ErrorController {


    @ResponseBody
    @RequestMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    public void handleError(HttpServletRequest request) {

        if (request.getAttribute(DispatcherServlet.EXCEPTION_ATTRIBUTE) != null) {
            throw new ProcessingRequestException(HttpStatus.BAD_REQUEST,
                    400, getDefaultMessagesFromException((Throwable)
                    request.getAttribute(DispatcherServlet.EXCEPTION_ATTRIBUTE)), null);
        }

        //empty body

        throw new ProcessingRequestException(HttpStatus.BAD_REQUEST, 401, "Unknown Exception");
    }

    public String getDefaultMessagesFromException(Throwable throwable) {

        if(throwable instanceof BindException) { //Not Null and not Blank variable exceptions
            return ((BindException) throwable).getBindingResult().getAllErrors().stream().map(
                    DefaultMessageSourceResolvable::getDefaultMessage).collect(
                    Collectors.joining("; "));
        }

        return "Unknown Exception";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
