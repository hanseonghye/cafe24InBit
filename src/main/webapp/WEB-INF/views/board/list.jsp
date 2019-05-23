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

	function page_move(url){
		var form = document.createElement("form")
		form.action=url;
		form.method="post"
		document.body.appendChild(form)
		form.submit();
	}

	$(function() {
		var jumpPage = ${jumppage};
		var nowPage = ${nowPage} -1;
		var countPage =${countPage}
		var pager = Number('${pager}')
		
		var nowBigPage = (parseInt(nowPage/3))*3 +1;
		if(nowBigPage != 1){
			$('#pager').append('<li><a href="${pageContext.servletContext.contextPath }/board/list/'+(nowBigPage-jumpPage)+'">◀</a></li>');
		}
		var isEndPage = false
		for (var i = nowBigPage; i <= (nowBigPage-1+jumpPage) ; ++i){
			var str = ""
			if ( i == (nowPage+1) ){
				str = '<li class="selected" id="pager-'+i+'"><a href="${pageContext.servletContext.contextPath }/board/list/'+i+'">'+i+"</a></li>"
			}else {
				str = '<li id="pager-'+i+'"><a href="${pageContext.servletContext.contextPath }/board/list/'+i+'">'+i+"</a></li>"
			}
			$('#pager').append(str)
			if(i == pager){
				isEndPage = true;
				break;
			}
		}
		
		if (isEndPage == false){
			$('#pager').append('<li><a href="${pageContext.servletContext.contextPath }/board/list/'+(nowBigPage+jumpPage)+'">▶</a></li>');
		}

	})
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board/search/1" method="post">
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
					<c:forEach items='${list }' var='vo' varStatus='status'>
					<tr>
						 <c:choose>
							<c:when test="${vo.status eq 'DIE' }">
								<td>${ vo.no}</td>
								<td>${vo.title } </td>
								<td> </td>								
								<td> </td>
								<td> </td>
								<td> </td>
							</c:when>
							<c:otherwise>
								<td>${vo.no }</td>
								<c:choose>
									<c:when test="${vo.depth == '0' }" >
										<td style="text-align: left;"><a href="${pageContext.servletContext.contextPath }/board/view/${vo.no}">${vo.title }</a></td>
									</c:when>
									<c:otherwise>
										<td style="text-align: left; padding-left:${vo.depth*20}px"><img src="${pageContext.servletContext.contextPath }/assets/images/reply.png"><a href="${pageContext.servletContext.contextPath }/board/view/${vo.no}">${vo.title }</a></td>
									</c:otherwise>
								</c:choose>
								<td>${vo.name }</td>
								<td>${vo.hit }</td>
								<td>${vo.reg_date }</td>
								<c:choose>
									<c:when test='${!empty authUser }'>
										<c:choose>
											<c:when test='${authUser.no eq vo.user_no }'>
												<td><a onclick="page_move('${pageContext.servletContext.contextPath }/board/delete/${vo.no}')" class="del">삭제</a></td>
											</c:when>
											<c:otherwise>
												<td></td>
											</c:otherwise>	
										</c:choose>
									</c:when>
									<c:otherwise>
										<td></td>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</tr>
					</c:forEach>
				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul id="pager">
					</ul>
				</div>

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