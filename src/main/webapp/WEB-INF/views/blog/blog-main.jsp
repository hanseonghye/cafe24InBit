<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>

<script>

</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/blog/includes/header.jsp" />
		<div id="wrapper">
			<div id="content">
			<c:choose>
				<c:when test='${!empty recentpost }'>
				<div class="blog-content">
					<h4>${ recentpost.title}</h4>
					<p>
						${recentpost.content}
					<p>
				</div>	
				</c:when>
			</c:choose>
				
				<ul class="blog-list">
					<c:forEach items='${post }' var='post' varStatus='status'>
						<li><a href="${pageContext.servletContext.contextPath }/${blog.id}/${post.cate_no}/${post.no}">${post.title }</a> <span>${post.reg_date }</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<c:choose>
					<c:when test='${blog.logo eq "spring-logo.jpg" }'>
						<img src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
					</c:when>
					<c:otherwise>
						<img src="${pageContext.request.contextPath}/${blog.logo }">
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items='${category }' var='cate' varStatus='status'>
					<li><a href="${pageContext.servletContext.contextPath }/${blog.id}/${cate.no}">${cate.name }</a></li>
				</c:forEach>

			</ul>
		</div>
		
		<jsp:include page="/WEB-INF/views/blog/includes/footer.jsp"></jsp:include>
	</div>
</body>
</html>