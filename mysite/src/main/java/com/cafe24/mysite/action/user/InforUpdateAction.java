package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mysite.dao.UserDao;
import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

public class InforUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pre_email = request.getParameter("pre_email");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		new UserDao().update(name, email, password, gender, pre_email);
		WebUtil.forward(request, response, "/WEB-INF/views/user/inforupdatesuccess.jsp");
	}

}
