package com.cafe24.mysite.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExeceptionHandler {
	@ExceptionHandler(UserDaoException.class)
	public void handleUserDaoException(HttpServletRequest request, HttpServletResponse response, Exception e) throws ServletException, IOException {
		//1. 로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		
		//2. 안내 페이지 가기 + 정상 종료
		request.setAttribute("uri",request.getRequestURI());
		request.setAttribute("exception", errors.toString());
		request.getRequestDispatcher("/WEB-INF/views/error/500.jsp").forward(request, response);
	}

	@ExceptionHandler(Exception.class)
	public void handleException(HttpServletRequest request, HttpServletResponse response, Exception e) throws ServletException, IOException {
		//1. 로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		System.out.println(errors.toString());
		
		//2. 안내 페이지 가기 + 정상 종료
		request.getRequestDispatcher("/WEB-INF/views/error/500.jsp").forward(request, response);
	}
	
//	@ExceptionHandler(GuestbookDaoException.class)
//	public String handleGuestbookDaoException() {
//		return "error/500";
//	}
	
}
