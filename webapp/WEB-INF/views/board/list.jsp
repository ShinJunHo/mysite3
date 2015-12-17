<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
		<!--  JSTL 변경 부분. -->
		<c:import url="/WEB-INF/views/include/header.jsp" />
				<div id="content">
					<div id="board">
						<form id="search_form" action="/mysite3/board/search?kw=${kw}" method="post">
							<input type="text" id="kw" name="kw" value=""> 
							<input type="submit" value="찾기">
						</form>
						<table class="tbl-ex">
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>조회수</th>
								<th>작성일</th>
								<th>&nbsp;</th>
							</tr>
							<c:set var="count" value="${fn:length(list.list)}"/>
							<c:forEach items="${list.list }" var="vo" varStatus="status">
								<tr>
									<td>${list.number - status.index}</td>
									<td class="title" style="padding-left:${vo.depth-1}*10px">
										<c:if test="${vo.depth > 1 }">
											<img src="/mysite3/assets/images/ico-reply.gif">
										</c:if>
											<a href="${pageContext.request.contextPath}/board/view/${vo.no}">${vo.title}</a>
									</td>
									<td>${vo.memberName }</td>
									<td>${vo.viewCount}</td>
									<td>${vo.regDate}</td>
									<td>
										<c:if test="${vo.memberNo == authUser.no }">
											<a href="${pageContext.request.contextPath}/board/delete/${vo.no}" class="del">삭제</a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</table>
						<div class="pager">
							<c:if test="${list.count > 0 }">
								<c:if test="${list.endPage > list.pageCount}">
									<c:set var="endPage" value="${list.pageCount}"/>
								</c:if>
								
								<ul>
									<c:if test="${list.numPageGroup > 1}">
										<li class="pg-prev"><a href="/mysite3/board/list?page=${(list.numPageGroup-2)*list.pageGroupSize+1}">◀ 이전</a></li>	
									</c:if>
									
									<c:forEach var="i" begin="${list.startPage}" end="${list.endPage}">
										<li><a href="/mysite3/board/list?page=${i}">${i}</a></li>
									</c:forEach>
									<c:if test="${list.numPageGroup < list.pageGroupCount }">
										<li class="pg-next"><a href="/mysite3/board/list?page=${list.numPageGroup*list.pageGroupSize+1}">다음 ▶</a></li>
									</c:if>
								</ul>
							</c:if>
							
							<!--  <ul>
								
								<li><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li class="disable">4</li>
								<li class="disable">5</li>
								<li class="pg-next"><a href="#">다음 ▶</a></li>
							</ul>
							-->
						</div>
						<div class="bottom">
							<a href="${pageContext.request.contextPath}/board/write" id="new-book">글쓰기</a>
						</div>
					</div>
				</div>
		<!--  JSTL 변경 부분. -->
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="board"></c:param>
		</c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
		<%
			//<jsp:include page="/WEB-INF/views/include/navigation.jsp"></jsp:include>
			//<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
		%>
	</div>
</body>
</html>