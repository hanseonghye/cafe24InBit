package com.cafe24.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mysite.action.guestbook.GuestbookActionFactory;
import com.cafe24.mysite.dao.GuestbookDao;
import com.cafe24.mysite.vo.GuestbookVo;
import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

/**
 * Servlet implementation class GusetbookServlet
 */
//@WebServlet("")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestbookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String actionName = request.getParameter("a");

		Action action = new GuestbookActionFactory().getAction(actionName);
		action.execute(request,response);
		
//		if("add".equals(actionName)) {
//
//			String name = request.getParameter("name");
//			String password = request.getParameter("password");
//			String contents = request.getParameter("contents");
//			GuestbookVo vo =new GuestbookVo();
//
//			vo.setPassword(password);
//			vo.setName(name);
//			vo.setContents(contents);
//			new GuestbookDao().insert(vo);
//			
//			WebUtil.forward(request, response,request.getContextPath() + "/guestbook");
//		}else if("deleteform".equals(actionName)) {
//			String no = request.getParameter("no");
//			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");
//		}else if("delete".equals(actionName)) {
//			String no = request.getParameter("no");
//			String password = request.getParameter("password");
//			request.setAttribute("no", no);
//			GuestbookVo vo =new GuestbookVo();
//			vo.setNo(Long.parseLong(no));
//			vo.setPassword(password);
//			new GuestbookDao().delete(vo);
//			WebUtil.forward(request, response, "/WEB-INF/views/guestbook");
//		}else {
//			GuestbookDao  dao = new GuestbookDao();
//			List<GuestbookVo> list = dao.getList();
//			request.setAttribute("list", list);
//			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
