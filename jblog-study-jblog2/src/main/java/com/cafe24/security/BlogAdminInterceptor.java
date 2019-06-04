package com.cafe24.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.UserVo;

public class BlogAdminInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); 
		String blog_id = (String)pathVariables.get("id");
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		if ( blog_id.equals(authUser.getId()) == false) {
			response.sendRedirect(request.getContextPath()+"/"+blog_id);
			return false;
		}
		
		return true;
	}

}
