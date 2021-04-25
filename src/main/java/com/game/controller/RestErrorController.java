package com.game.controller;

import com.game.exception.ProcessingRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class RestErrorController implements ErrorController {

    private static final Logger log = LoggerFactory.getLogger(RestErrorController.class);

    @ResponseBody
    @RequestMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    public void handleError(HttpServletRequest request) {

        if (request.getAttribute(RequestDispatcher.ERROR_EXCEPTION) != null) {
            log.error("Unexpected exception has been thrown: {}", request.getAttribute(RequestDispatcher.ERROR_EXCEPTION));
        }

        if (request.getAttribute(DispatcherServlet.EXCEPTION_ATTRIBUTE) != null) {
            throw new ProcessingRequestException(HttpStatus.BAD_REQUEST,
                    400, getDefaultMessagesFromException((Throwable)
                    request.getAttribute(DispatcherServlet.EXCEPTION_ATTRIBUTE)), null);
        } else if (request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE) != null && request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).equals(
                HttpServletResponse.SC_NOT_FOUND)) { // incorrect URI
            throw new ProcessingRequestException(HttpStatus.BAD_REQUEST,
                    400, "Invalid URI", null);
        }

        //TODO: empty body exception

        throw new ProcessingRequestException(HttpStatus.BAD_REQUEST, 601, "Unknown Exception");
    }

    public String getDefaultMessagesFromException(Throwable throwable) {

        if(throwable instanceof BindException) { //Not Null and not Blank variable exceptions
            return ((BindException) throwable).getBindingResult().getAllErrors().stream().map(
                    DefaultMessageSourceResolvable::getDefaultMessage).collect(
                    Collectors.joining("; "));
        } else if(throwable instanceof HttpRequestMethodNotSupportedException) { // incorrect request methods
            return throwable.getMessage() + ". Supported methods for URI are: " + Arrays
                    .toString(((HttpRequestMethodNotSupportedException) throwable)
                            .getSupportedMethods());
        } else if(throwable instanceof HttpMessageNotReadableException) { // incorrect json types
            return ((HttpMessageNotReadableException) throwable).getMessage()
                    .substring(0, ((HttpMessageNotReadableException) throwable).getMessage().indexOf(';'));
        }

        return "Unknown Exception";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
