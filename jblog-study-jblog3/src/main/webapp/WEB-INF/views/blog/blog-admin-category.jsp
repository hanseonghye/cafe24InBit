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

	$(function() {

		$('#add-category').click(
			function(){
				console.log("click")
				var name = $('#name').val();
				var description = $('#description').val();
				
				if (name == ''){
					return ;
				}
				
				if(description == ''){
					return ;
				}

				$.ajax({
					url : "${pageContext.servletContext.contextPath }/${blog_id }/admin/addcategory",
					type : "post",
					dataType:"json",
					data : {
						name:name,
						description:description
					},
					success : function(response){
						
						$('#name').empty();
						$('#description').empty();
						
						if(response.message == "NO"){
							alert("이미 있는 카테고리 있니다.")
							return ;
						}
						$('#admin-cat tbody').empty();
						
						appendTr(response.data)
					},
					error : function(xhr, error) {
						console.log(error);

					}
				})
			}
		)
	})
	
	
	function category_delete(id){
		
		if ($('#admin-cat tbody tr').length == 1){
			alert("최소한 하나의 카테고리가 있어야 합니다 ~")
			return ;
		}
		
		$.ajax({
			url : "${pageContext.servletContext.contextPath }/${blog_id}/admin/delete/"+id,
			type : "post",
			dataType : "json",
			data :{},
			success : function(response){
				if(response.message == "OK"){
					$('#admin-cat tbody').empty();
					appendTr(response.data)
				}
			},
			error : function(xhr, error){
				console.log(error)
			}
		})
	}
	
	
	function appendTr(list){
		var table = document.getElementById("admin-cat");
		var tbody = table.getElementsByTagName("tbody")[0];
		
		
		for(var i=0;i<list.length; ++i){
			var row = document.createElement("tr");
			var cel1 = document.createElement("td");
			var cel2 = document.createElement("td");
			var cel3 = document.createElement("td");
			var cel4 = document.createElement("td");
			var cel5 = document.createElement("td");
			
			cel1.innerHTML = i+1;
			cel2.innerHTML = list[i].name;
			cel3.innerHTML = list[i].count;
			cel4.innerHTML = list[i].description;
			//cel5.innerHTML = '<a href="${pageContext.servletContext.contextPath }/blog/${authUser.id}/admin/delete/'+list[i].no+'"><img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a>';
			cel5.innerHTML = '<img  id="'+list[i].no+'"'+' src="${pageContext.request.contextPath}/assets/images/delete.jpg" onclick="category_delete(this.id)">';

			row.appendChild(cel1);
			row.appendChild(cel2);
			row.appendChild(cel3);
			row.appendChild(cel4);
			row.appendChild(cel5);
			
			tbody.appendChild(row);
		}
	}
	
	
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/blog/includes/header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/blog/includes/admin-header.jsp" />
				<table class="admin-cat" id="admin-cat">
					<thead>
						<tr>
							<th>번호</th>
							<th>카테고리명</th>
							<th>포스트 수</th>
							<th>설명</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items='${categoryList }' var='category'
						varStatus='status'>
						<tr>
							<td>${status.count }</td>
							<td>${category.name }</td>
							<td>${category.count }</td>
							<td>${category.description }</td>
							<td><img id="${category.no}" src="${pageContext.request.contextPath}/assets/images/delete.jpg" onclick="category_delete(this.id)"></a></td>
						</tr>
					</c:forEach>
					</tbody>

				</table>

				<h4 class="n-c">새로운 카테고리 추가</h4>
				<table id="admin-cat-add">
					<tr>
						<td class="t">카테고리명</td>
						<td><input type="text" id="name" name="name"></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input type="text" id="description" name="description"></td>
					</tr>
					<tr>
						<td class="s">&nbsp;</td>
						<td><button type="button" id="add-category">카테고리 추가</button></td>
					</tr>
				</table>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/blog/includes/footer.jsp"></jsp:include>
	</div>
</body>
</html>