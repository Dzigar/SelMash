package com.selfmash.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.selfmash.model.Dialog;
import com.selfmash.model.User;
import com.selfmash.service.DialogService;
import com.selfmash.service.UserService;

@Controller
@RequestMapping("/dialog")
public class DialogController {

    private Dialog dialog;

    @Autowired
    private UserService userService;

    @Autowired
    private DialogService dialogService;

    @RequestMapping(method = RequestMethod.GET)
    public String createDialog(HttpServletRequest request, ModelMap modelMap,
            Principal principal) {
        User userSender = userService.getUserByLogin(principal.getName());
        User userReceiver = userService.getUserByLogin(request.getSession()
                .getAttribute("userLogin").toString());
        dialog = dialogService.getDialogByUsers(userSender, userReceiver);
        if (dialog == null) {
            dialog = new Dialog(userSender, userReceiver);
            dialogService.saveDialog(dialog);
        }
        request.getSession().setAttribute("userSender", userSender);
        request.getSession().setAttribute("userReceiver", userReceiver);
        return "redirect:/dialog/" + dialog.getId();
    }

    @RequestMapping(value = "/{dialogId}", method = RequestMethod.GET)
    public String dialogPage(@PathVariable Long dialogId, ModelMap map) {
        map.addAttribute("dialogId", dialogId);
        return "dialog_page";
    }
}
