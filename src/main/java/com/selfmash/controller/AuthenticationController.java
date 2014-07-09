package com.selfmash.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.selfmash.model.City;
import com.selfmash.model.User;
import com.selfmash.service.StateService;

@Controller
public class AuthenticationController {

    @Autowired
    private StateService stateService;

    /**
     * 
     * @param model
     * @return access_page.jsp
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public final String viewRegistration(final Map<String, Object> model) {
        model.put("user", new User());
        model.put("stateList", stateService.getAllStates());
        return "access_page";
    }

    /**
     * @return redirect to index page
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public final String logout() {
        return "redirect:login";
    }

    /**
     * @return login.jsp
     */
    @RequestMapping(value = "/error-login", method = RequestMethod.GET)
    public final String errorLogin() {
        return "login";
    }

    @RequestMapping(value = "/getCities", method = RequestMethod.GET)
    @ResponseBody
    public List<City> getCities(@RequestParam("countryId") long countryId,
            HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        return stateService.getCitiesByState(countryId);
    }

}
