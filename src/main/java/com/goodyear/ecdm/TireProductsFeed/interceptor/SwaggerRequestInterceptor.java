package com.goodyear.ecdm.TireProductsFeed.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class SwaggerRequestInterceptor extends HandlerInterceptorAdapter {


	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, 
		HttpServletResponse response, Object object) throws Exception {
		System.out.println("***************************************************");
		System.out.println(":: In preHandle for SwaggerInterceptor :: ");
		String requestURI = request.getRequestURI();
		System.out.println("RequestURI::" + requestURI);
		
		String keyVal	= request.getParameter("key");
		System.out.println("keyVal >> '" + keyVal+"'");
		boolean returnval=false;
		if (keyVal != null && keyVal.length() > 0) {
			returnval = true;
		}
		System.out.println("returnval: " + returnval);
		
		System.out.println("***************************************************");
		return returnval;
	}
	
}
