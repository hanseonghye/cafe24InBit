<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/includes/header.jsp'/>
		<div id="content">
			<div id="user">
				<c:if test='${not empty authUser }'>
					<p class="jr-success">
						${ authUser.name } 님 안녕하세요 
					</p>
				</c:if>
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		</c:import>
		<c:import url='/WEB-INF/views/includes/footer.jsp' />
	</div>
</body>
</html>