package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mysite.action.user.JoinAction;
import com.cafe24.mysite.action.user.JoinFormAction;
import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

public class UserAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		WebUtil.forward(request, response, request.getContextPath());
		
	}

}
