<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
<!--  tag lib 선언 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
			<%
				pageContext.setAttribute("newline","\n");
			%>

				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${board.title }</td>
					</tr>
					<c:if test="${not empty board.fileName}">
					<tr>
						<td class="label">첨부파일</td>
						<td><img src="${pageContext.request.contextPath}${board.fileName}" style="width:150px"></td>
					</tr>
					</c:if>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${fn:replace(board.content,newline,"<br/>")}
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<c:if test="${not empty authUser}">
						<a href="${pageContext.request.contextPath}/board/reply?no=${board.no}">답글</a>
					</c:if>
					<a href="${pageContext.request.contextPath}/board/list">글목록</a> 
					<c:if test="${board.memberNo == authUser.no }">
						<a href="${pageContext.request.contextPath}/board/modify?no=${board.no}">글수정</a>
					</c:if>
					
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="board"></c:param>
		</c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>