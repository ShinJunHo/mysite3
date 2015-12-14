<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>




<!--  import는 지워도 된다. JSTL EL 로 세션을 처리했기에. -->
<!--  tag lib 선언 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
//	UserVo authUser = (UserVo) session.getAttribute("authUser");
	//jsp는 이렇게 쓰지만 서블릿에서는 request.getSession();
%>
<div id="header">
	<h1>MySite</h1>
	<ul>
		<c:choose>
			<c:when test="${empty authUser}">
				<li><a href="${pageContext.request.contextPath}/user/loginform">로그인</a>
				<li>
				<li><a href="${pageContext.request.contextPath}/user/joinform">회원가입</a>
				<li>
			</c:when>
			<c:otherwise>
				<li><a href="">회원정보수정</a>
				<li>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a>
				<li>
				<li>${sessionScope.authUser.name}님안녕하세요^^;</li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>