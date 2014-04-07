package com.selfmash.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AccountingInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.info("Client User-Agent: " + request.getHeader("User-Agent"));

		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();

		if (userName.equals("anonymousUser")) {
			userName = "";
		}

		try {
			request.setAttribute("username", userName);
		} catch (Exception e) {
			logger.info(e.getLocalizedMessage());
		}

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		super.afterCompletion(request, response, handler, ex);
	}
}
