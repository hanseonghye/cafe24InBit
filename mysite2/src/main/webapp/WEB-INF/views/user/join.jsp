<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/user.css"
	rel="stylesheet" type="text/css">
<script
	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>

	$(function() {
		$('#check-button')
				.click(
						function() {
							$('#email').change(function() {
								$('#check-button').show();
								$('check-image').hide();
							})

							var email = $('#email').val()
							if (email == '') {
								return;
							}
							
							//ajax 통신
							$.ajax({
									url : "${pageContext.servletContext.contextPath}/user/api/checkemail?email="
											+ email,
									type : "get",
									dataType : "json",
									data : "",
									success : function(response) {
										
										if (response.result != "success") {
											console.error(response.message);
											return;
										}

										if (response.data == true) {
											alert("이미 존재하는 이메일 입니다.")
											$("#email").focus();
											$("#email").val("");
											return;
										}
										
										if (response.data == false) {
											$('#check-button').hide();
											$('#check-image').show();
										}
									},
									error : function(xhr, error) {
										console.error(error);
									}
								})

						})
	})
</script>
</head>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/includes/header.jsp' />
		<div id="content">
			<div id="user">

				<form:form 
					modelAttribute="userVo"
					id="join-form" name="joinForm" method="post"
					action="${pageContext.servletContext.contextPath }/user/join">
					<label class="block-label" for="name">이름</label> 
					<!-- <input id="name" name="name" type="text" value="" required>  -->
					<form:input path="name"/>
					<p style="color:red; text-align:left; padding:0; margin:0;">
						<form:errors path="name"/>
					</p>
					<%-- <spring:hasBindErrors name="userVo">
					    <c:if test="${errors.hasFieldErrors('email') }">
					    <p style="color:red; text-align:left">
						        <spring:message 
						     code="${errors.getFieldError( 'name' ).codes[0] }" text="${errors.getFieldError( 'name' ).defaultMessage }" />
					    </p>
					   </c:if>
					</spring:hasBindErrors> --%>
					
					<label class="block-label" for="email">이메일</label>
					<form:input path="email"/>
					<!-- <input id="email" name="email" type="text" value="" required>  -->
					<input type="button" value="체크" id="check-button">
					<img style="display: none" id="check-image"
						src="${pageContext.servletContext.contextPath }/assets/images/check_icon.png" />
					<p style="color:red; text-align:left; padding:0; margin:0;">
						<form:errors path="email"/>
					</p>
					<label class="block-label">패스워드</label> 
					<!-- 
						<input name="password"
						type="password" value="" required>
 					-->
 					<form:password path="password"/>
 					<form:errors path="password"/>
 					
 					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <form:radiobutton path="gender" value="female" checked="checked"/> 
						<label>남</label> <form:radiobutton path="gender" value="male"/> 
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="가입하기">

				</form:form>
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		</c:import>
		<c:import url='/WEB-INF/views/includes/footer.jsp' />
	</div>
</body>
</html>