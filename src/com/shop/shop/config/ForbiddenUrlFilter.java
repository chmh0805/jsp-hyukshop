package com.shop.shop.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForbiddenUrlFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		if (request.getRequestURI().equals("/shop/")) {
			chain.doFilter(request, response);
		} else if (request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")).equals("/jusoPopup.jsp")) { 
			chain.doFilter(request, response);
		} else if (request.getRequestURI().equals("/index.jsp")){
			chain.doFilter(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
	}
}
