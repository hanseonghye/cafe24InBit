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
		<c:import url='/WEB-INF/views/includes/header.jsp' />
		<div id="content">
			<div id="user">
				<form id="inforUpdate-form" name="inforUpdateForm" method="post" action="${pageContext.servletContext.contextPath }/user/inforupdate">
					<input type="hidden" name="pre_email" value='${ authUser.email }'>
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value=${ authUser.name } required>
					
					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value='${ authUser.email }' required>
					<input type="button" value="id 중복체크">
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="" required>
					
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
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		</c:import>
		<c:import url='/WEB-INF/views/includes/footer.jsp' />
	</div>

</body>
</html>