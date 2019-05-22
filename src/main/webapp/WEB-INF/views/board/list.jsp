<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
	
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
	$(function() {
		var nowPage = ${nowPage} -1;
		var countPage =${countPage}
		var pager = Number('${pager}')
		var jsonData = ${jsonlist}
		jsonData.sort(function(a,b){
			if (parseFloat(b.group_no) > parseFloat(a.group_no)){
				return 1;
			}else if (parseFloat(b.group_no) < parseFloat(a.group_no)){
				return -1;
			}else{
				return parseFloat(a.order_no) - parseFloat(b.order_no)
			}
		})
		
		
		for (var i =0; i<jsonData.length; ++i){
			var str = '<tr>'
			str = str +'<td>'+(nowPage*countPage+i+1)+'</td>'
			if (jsonData[i].depth != 0 ){
				str = str +'<td style="text-align: left;'+'padding-left:'+jsonData[i].depth*20+'px">'+'<img src="${pageContext.servletContext.contextPath }/assets/images/reply.png">'+'<a href="${pageContext.servletContext.contextPath }/board/view/'+jsonData[i].no+'">'+jsonData[i].title+'</a></td>'
			}else{
				str = str +'<td style="text-align: left;"><a href="${pageContext.servletContext.contextPath }/board/view/'+jsonData[i].no+'">'+jsonData[i].title+'</a></td>'
			} 
			str = str + '<td>' + jsonData[i].name+'</td>'
			str = str + '<td>' + jsonData[i].hit+'</td>'
			str = str + '<td>' + jsonData[i].reg_date+'</td>'
			str = str + '<td><a href="${pageContext.servletContext.contextPath }/board/delete/'+jsonData[i].no+'" class="del">삭제</a></td>'
			str = str+'</tr>'
			$('#tbl-ex').append(str);	
		}
		
		
		
		$('#pager').append('<li><a href="">◀</a></li>');
		for (var i =1; i<=pager; ++i){
			var str = '<li id="pager-'+i+'"><a href="${pageContext.servletContext.contextPath }/board/list/'+i+'">'+i+"</a></li>"
			$('#pager').append(str)
		}
		
		$('#pager').append('<li><a href="">▶</a></li>');
		
		$('#pager li').click(
			function(){
				
			}		
		)
	})
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board/search" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex" id="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>

				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul id="pager">
<!-- 						<li><a href="">◀</a></li>
						<li><a href="">▶</a></li> -->
					</ul>
				</div>
				<!-- pager 추가 -->

				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath }/board/write" id="new-book">글쓰기</a>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>