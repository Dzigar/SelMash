package com.selfmash.controller;

import java.security.Principal;
import java.util.Map;

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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.selfmash.model.User;
import com.selfmash.service.PostService;
import com.selfmash.service.RoleService;
import com.selfmash.service.UserService;

/**
 * SecurityController class.
 * 
 * @author Dzigar
 * 
 */

@Controller
@RequestMapping("/")
public class SecurityController {

    /**
     * Logger for SecurityController class.
     */
    private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * id user role in DB.
     */
    private final long USER_ROLE_ID = 2;

    /**
     * 
     */
    @Resource(name = "userServiceImpl")
    private UserService userService;

    /**
     * 
     */
    @Resource(name = "roleService")
    private RoleService roleService;

    /**
     * 
     */
    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    /**
     * 
     */
    @Autowired
    @Qualifier("org.springframework.security.authenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    private PostService postService;

    /**
     * 
     * @param request
     *            HttpServletRequest object
     * @return redirect to index.jsp
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public final String openIndexPage(final HttpServletRequest request) {
        return "redirect:/index";
    }

    /**
     * 
     * @return index.jsp
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public final String welcomePage(Principal principal, ModelMap modelMap) {
        try {
            modelMap.addAttribute(
                    "posts",
                    postService.getFollowPosts(userService.getUserByLogin(
                            principal.getName()).getId()));
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }

        return "index";
    }

    /**
     * 
     * @param model
     *            -
     * @return access_page.jsp
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public final String viewRegistration(final Map<String, Object> model) {
        model.put("user", new User());
        return "access_page";
    }

    /**
     * @return redirect to index page
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public final String logout() {
        return "redirect:index";
    }

    /**
     * @return login.jsp
     */
    @RequestMapping(value = "/error-login", method = RequestMethod.GET)
    public final String errorLogin() {
        return "login";
    }

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
