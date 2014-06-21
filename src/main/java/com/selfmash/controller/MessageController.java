package com.selfmash.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.selfmash.model.Message;
import com.selfmash.service.UserService;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public @ResponseBody
    Message sendMessage(HttpServletRequest request, Principal principal) {
        Message message = new Message(request.getParameter("message"),
                userService.getUserByLogin(principal.getName()));
        return message;
    }
}
