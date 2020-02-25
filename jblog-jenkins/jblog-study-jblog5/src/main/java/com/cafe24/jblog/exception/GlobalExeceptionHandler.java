package com.cafe24.jblog.exception;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cafe24.jblog.dto.JSONResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExeceptionHandler {

	@ExceptionHandler(Exception.class)
	public void handleException(HttpServletRequest request, HttpServletResponse response, Exception e)
			throws ServletException, IOException {
		// 1. 로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
//		 LOGGER.error(errors.toString());
		String accept = request.getHeader("accept");

		if (accept.matches(".*application/json.*")) {
			//json 응답
			response.setStatus(HttpServletResponse.SC_OK);
			JSONResult result = JSONResult.fail(errors.toString());
			String resultnew = new ObjectMapper().writeValueAsString(result);
			System.out.println(resultnew);
			OutputStream os =  response.getOutputStream();
			os.write(resultnew.getBytes("UTF-8"));
			os.flush();
			os.close();
			errors.close();
		} else {
			// 2. 안내 페이지 가기 + 정상 종료
			request.setAttribute("uri", request.getRequestURI());
			request.setAttribute("exception", errors.toString());
			request.getRequestDispatcher("/WEB-INF/views/error/500.jsp").forward(request, response);
			errors.close();
			// return "error/500"; 이랑 같다

		}
	}


}
