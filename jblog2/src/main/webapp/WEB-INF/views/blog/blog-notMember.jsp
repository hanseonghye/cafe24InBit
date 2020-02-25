<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script
	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>

<script>
	
</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>없는 블로그 입니다.</h1>
			<ul>
				<c:choose>

					<c:when test='${empty authUser }'>
						<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
					</c:when>

					<c:otherwise>
						<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
						<li><a
							href="${pageContext.request.contextPath}/${authUser.id}">내블로그
								가기</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div id="footer">
			<p>
				powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>