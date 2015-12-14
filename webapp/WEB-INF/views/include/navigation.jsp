<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!--  tag lib 선언 -->
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<div id="navigation">
	<ul>
		<!-- <li class="selected"> -->
		<c:choose>
			<c:when test="${param.menu == 'main' }">
				<li class="selected"><a href="${pageContext.request.contextPath}/">Main</a></li>
				<li><a href="${pageContext.request.contextPath}/guestbook/list">방명록</a></li>
				<li><a href="${pageContext.request.contextPath}/board/list">게시판</a></li>
			</c:when>

			<c:when test="${param.menu == 'guestbook' }">
				<li><a href="${pageContext.request.contextPath}/">Main</a></li>
				<li class="selected"><a href="${pageContext.request.contextPath}/guestbook/list">방명록</a></li>
				<li><a href="${pageContext.request.contextPath}/board/list">게시판</a></li>
			</c:when>

			<c:when test="${param.menu == 'board' }">
				<li><a href="${pageContext.request.contextPath}/">Main</a></li>
				<li><a href="${pageContext.request.contextPath}/guestbook/list">방명록</a></li>
				<li class="selected"><a href="${pageContext.request.contextPath}/board/list">게시판</a></li>
			</c:when>

		</c:choose>
	</ul>
</div>