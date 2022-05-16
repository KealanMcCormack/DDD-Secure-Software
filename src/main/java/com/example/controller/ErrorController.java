package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String viewErrorPage(){
        return "error";
    }

}
