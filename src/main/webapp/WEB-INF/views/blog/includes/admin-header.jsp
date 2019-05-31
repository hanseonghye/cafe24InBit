<%@page import="com.cafe24.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<ul class="admin-menu">
		<li><a href="${pageContext.servletContext.contextPath }/${authUser.id}/admin/basic">기본설정</a></li>
		<li><a href="${pageContext.servletContext.contextPath }/${authUser.id}/admin/category">카테고리</a></li>
		<li><a href="${pageContext.servletContext.contextPath }/${authUser.id}/admin/write">글작성</a></li>
		<li><a href="${pageContext.request.contextPath}/${blog_id}">블로그 메인</a></li>
</ul>
