package com.cafe24.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mysite.dao.GuestbookDao;
import com.cafe24.mysite.vo.GuestbookVo;
import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

public class AddAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String contents = request.getParameter("contents");
		
		GuestbookVo vo =new GuestbookVo();
		vo.setPassword(password);
		vo.setName(name);
		vo.setContents(contents);
		new GuestbookDao().insert(vo);
		
		WebUtil.redirect(request, response,  request.getContextPath()+"/guestbook");
	}

}
