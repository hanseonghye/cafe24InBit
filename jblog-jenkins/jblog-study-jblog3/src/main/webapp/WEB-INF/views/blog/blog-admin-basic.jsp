<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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

$(function(){
	$("#logo-file").change(function(){
		readURL(this);
	})
	
	function readURL(input){
		if(input.files && input.files[0]){
			var reader = new FileReader();
			reader.onload = function(e){
				$('#logo-img').attr('src',e.target.result);
			}
			
			reader.readAsDataURL(input.files[0]);
		}
	}
})
</script>
</head>

<body>
	<div id="container">
		<c:import url="/WEB-INF/views/blog/includes/header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/blog/includes/admin-header.jsp" />
				<form action="${pageContext.request.contextPath }/${authUser.id}/admin/basic" method="post" enctype="multipart/form-data">
					<table class="admin-config">
						<tr>
							<td class="t">블로그 제목</td>
							<td><input type="text" size="40" name="title"></td>
						</tr>
						<tr>
							<td class="t">로고이미지</td>
							<td>
							<c:choose>
								<c:when test='${blog.logo eq "spring-logo.jpg" }'>
									<img id="logo-img" src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
								</c:when>
								<c:otherwise>
									<img id="logo-img" src="${pageContext.request.contextPath}/${blog.logo }">
								</c:otherwise>
							</c:choose>
							</td>
						</tr>
						<tr>
							<td class="t">&nbsp;</td>
							<td><input type="file" id="logo-file" name="logo-file"></td>
						</tr>
						<tr>
							<td class="t">&nbsp;</td>
							<td class="s"><input type="submit" value="기본설정 변경"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/blog/includes/footer.jsp"></jsp:include>
	</div>
</body>
</html>