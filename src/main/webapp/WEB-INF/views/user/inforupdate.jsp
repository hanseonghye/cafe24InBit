<%@page import="com.cafe24.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%
	UserVo authUser = (UserVo) session.getAttribute("authUser");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath() %>/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		<div id="content">
			<div id="user">
				<form id="inforUpdate-form" name="inforUpdateForm" method="post" action="<%=request.getContextPath() %>/user?a=inforupdate">
					<input type="hidden" name="a" value="inforupdate">
					<input type="hidden" name="pre_email" value="<%=authUser.getEmail() %>">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="<%=authUser.getName()%>">
					
					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="<%=authUser.getEmail()%>">
					<input type="button" value="id 중복체크">
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의 합니다.</label>
					</fieldset>
					
					<input type="submit" value="수정하기">
				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
	</div>

</body>
</html>