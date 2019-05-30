<%@page import="com.cafe24.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="header">
	<h1>${blog.title }</h1>
	<ul>
		<c:choose>

			<c:when test='${empty authUser }'>
				<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
			</c:when>

			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				<c:choose>
					<c:when test='${ authUser.id eq blog_id }'>
						<li><a
							href="${pageContext.servletContext.contextPath }/blog/${authUser.id}/admin/basic">블로그
								관리</a></li>
					</c:when>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
