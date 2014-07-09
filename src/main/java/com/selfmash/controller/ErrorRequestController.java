package com.selfmash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorRequestController {

    /**
     * 
     * @return 404.jsp
     */
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String pageNotFound() {
        return "404";
    }
}
