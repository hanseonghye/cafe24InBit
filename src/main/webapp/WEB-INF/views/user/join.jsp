<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
console.log("11")
$(function(){
	console.log("Ddd")
	$('#btn-checkemail').click(
			function(){
				var id = $('#id').val();
				if ( id == ''){
					alert("아이디를 입력해 주세요.")
					return ;
				}
				
				$.ajax({
					url : "${pageContext.servletContext.contextPath}/ss/api/checkid?id="+id,
					type : "get",
					dataType :"json",
					data :"",
					success : function(response){
						if(response.message == "OK"){
							if(response.data == false){  //없음
								$("#btn-checkemail").hide();
								$('#submit').attr('disabled', false);
							}else {
								alert("이미 존재하는 아이디입니다.");
							}
						}	
					},
					error : function(xhr, error){
						console.log(error);
					}
				})
		}
	)
	
	
	
})
</script>
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<ul class="menu">
			<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		</ul>
		<form:form modelAttribute="userVo" class="join-form" id="join-form" method="post" 
			action="${pageContext.servletContext.contextPath }/user/join">
			<label class="block-label" for="name">이름</label> 
			<form:input path="name"/>
			<p style="color:red; text-align:left; padding:0; margin:0;">
				<form:errors path="name"/>
			</p>
			<label class="block-label" for="id">아이디</label> 
			
			<form:input path="id"/>
			<button id="btn-checkemail" type="button">id 중복체크</button>
			<p style="color:red; text-align:left; padding:0; margin:0;">
				<form:errors path="id"/>
			</p>

			<label class="block-label" for="password">패스워드</label> 
			<form:password path="password"/>
			<p style="color:red; text-align:left; padding:0; margin:0;">
				<form:errors path="password"/>
			</p>
			
			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기" id="submit" disabled="true">

		</form:form>
	</div>
</body>
</html>
