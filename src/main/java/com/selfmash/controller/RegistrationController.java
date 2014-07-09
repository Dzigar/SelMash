package com.selfmash.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.selfmash.model.User;
import com.selfmash.service.RoleService;
import com.selfmash.service.StateService;
import com.selfmash.service.UserService;

@SuppressWarnings("deprecation")
@Controller
public class RegistrationController {

    @Autowired
    private StateService stateService;

    @Resource(name = "roleService")
    private RoleService roleService;

    @Autowired
    @Qualifier("org.springframework.security.authenticationManager")
    private AuthenticationManager authenticationManager;

    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    /**
     * Id user role in DB.
     */
    private final long USER_ROLE_ID = 2;

    private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * Register new User.
     * 
     * @param user
     *            - object User class.
     * @param result
     *            - object BindingResult class.
     * @param request
     *            - object HttpServletRequest class.
     * @return redirect to index.jsp if user identified and redirect to
     *         login.jsp if user login failed
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public final String addUser(@Valid final User user,
            final BindingResult result, final HttpServletRequest request) {

        try {
            if (result.hasErrors()) {
                logger.error("Error valigation registration : "
                        + result.getErrorCount());
                logger.error(result.getFieldError());
                return "access_page";
            } else {
                user.setCity(stateService.getCityByName(request
                        .getParameter("userCity")));
                user.setRole(roleService.getRole(USER_ROLE_ID));
                String password = user.getPassword();
                user.setPassword(passwordEncoder.encodePassword(password,
                        user.getLogin()));
                userService.addUser(user);
                addUserToAuthenticatedList(user.getLogin(), password);
            }
        } catch (Exception e) {
            logger.info(e.getLocalizedMessage());
            return "redirect:/login";
        }
        return "redirect:index";
    }

    /**
     * Add user after registration to authenticated list.
     * 
     * @param login
     *            - user login
     * @param pass
     *            - user password
     */
    private void addUserToAuthenticatedList(final String login,
            final String pass) {
        SecurityContextHolder.getContext().setAuthentication(
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(
                                login, pass)));
    }

}
