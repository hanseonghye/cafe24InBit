<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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

				<form id="join-form" name="joinForm" method="post" action="<%=request.getContextPath() %>/user?a=login">
					<input type="hidden" name="a" value="join">
					<label class="block-label" for="name">�̸�</label>
					<input id="name" name="name" type="text" value="">

					<label class="block-label" for="email">�̸���</label>
					<input id="email" name="email" type="text" value="">
					<input type="button" value="id �ߺ�üũ">
					
					<label class="block-label">�н�����</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>����</legend>
						<label>��</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>��</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<fieldset>
						<legend>�������</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>���� ����� �����մϴ�.</label>
					</fieldset>
					
					<input type="submit" value="�����ϱ�">
					
				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
	</div>
</body>
</html>