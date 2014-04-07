package com.selfmash.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.selfmash.service.RequestService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Resource(name = "requestServiceImpl")
	private RequestService requestService;

	private Logger logger = Logger.getLogger(getClass().getName());

	@RequestMapping(method = RequestMethod.GET)
	public String showAdminPage(HttpServletRequest httpServletRequest,
			ModelMap map) {
		try {
			String str = httpServletRequest.getParameter("requestPage");
			if (str != null) {
				map.addAttribute("requestPage", str);
			} else
				map.addAttribute("requestPage", 1);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
		return "admin_page";
	}

	@RequestMapping(value = "/sendRequest1", method = RequestMethod.POST)
	public String performFirstRequest(HttpServletRequest servletRequest,
			ModelMap map) {
		map.addAttribute("requestPage", "1");
		try {
			String dateFrom = servletRequest.getParameter("yearFrom") + "-"
					+ servletRequest.getParameter("monthFrom") + "-"
					+ servletRequest.getParameter("dayFrom");
			String dateEnd = servletRequest.getParameter("yearTo") + "-"
					+ servletRequest.getParameter("monthTo") + "-"
					+ servletRequest.getParameter("dayTo");
			List<Object[]> userParams = requestService.firstRequest(
					new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
							.parse(dateFrom), new SimpleDateFormat(
							"yyyy-MM-dd", Locale.ENGLISH).parse(dateEnd));
			map.addAttribute("userlist", userParams);

		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
		return "admin_page";
	}

	@RequestMapping(value = "/sendRequest2", method = RequestMethod.POST)
	public String performSecondRequest(HttpServletRequest servletRequest,
			ModelMap map) {
		map.addAttribute("formRequest", "1");
		return "admin_page";
	}
}