<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--  tag lib 선언 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet"
	type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js "></script>
	
	<script type="text/javascript">
	$(function(){
		$("#email").change(function(){
			$("#btn-checked").show();
			$("image-checked").hide();
		});
		
		//Jquery의 Main이라고 생각하면된다.
		//console.log("jQuery!!")
		$("#btn-checkemail").click(function(){
			var email=$("#email").val();
			if(email==""){
				return;
			}
			//console.log("clicked!!!");
			$.ajax({
				//url:"/mysite3/api/user/checkemail"
				url:"${pageContext.request.contextPath}/api/user/checkemail",
				type:"get",
				dataType:"json",
				//이 값을 파라미터로 받아와야한다.
				data:"email="+email,
				//contentType:"application/json"
				//서버쪽에 데이터를 보낼때 데이터 타입을 말하는 것이다.
				success:function(response){
					//성공하고 결과가 날라왔을댄 결과를 response에 넣어줘라.
					//response가 어떻게 나오는지 찍어보쟈
					console.log(response);
					
					if(response.result == "fail"){
						console.error(response.message);
						return;
					}
					if(response.data == false){
						alert("이미 사용중인 이메일 입니다.");
						var $email=$("#email");
						$email.val("");
						$email.focus();
						return;
					}
					$("#btn-checkemail").hide();
					$("#image-checked").show();
					
				},
				error:function(jqXHR,status,error){
					console.error(status+":"+error);
				}
			});
		});
		
	
	});
	
	
	
	
	
	
	
	
	</script>
	
	
</head>
<body>
	<div id="container">
		<!--  JSTL 변경 부분. -->
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post"
					action="${pageContext.request.contextPath}/user/join">
					<input type="hidden" name="a" value="join"> 
					<label class="block-label" for="name">이름</label> 
					<input id="name" name="name" type="text" value=""> 
					<label class="block-label" for="email">이메일</label> 
					<input id="email" name="email" type="text" value=""> 
					<img id="image-checked" src="${pageContext.request.contextPath}/assets/images/checked.png" style="width:12px; display:none">
					<input id="btn-checkemail" type="button" value="id 중복체크">
					<label class="block-label">패스워드</label> 
					<input name="password" type="password" value="">

					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="male">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="가입하기">

				</form>
			</div>
		</div>
		<!--  JSTL 변경 부분. -->
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="guestbook"></c:param>
		</c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp" />

		<%
			//<jsp:include page="/WEB-INF/views/include/navigation.jsp"></jsp:include>
			//<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
		%>
	</div>
</body>
</html>