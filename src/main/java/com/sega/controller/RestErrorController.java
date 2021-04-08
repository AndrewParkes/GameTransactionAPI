package com.sega.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RestErrorController implements ErrorController {


    @ResponseBody
    @RequestMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    public void handleError(HttpServletRequest request) {

        //log something...

    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
