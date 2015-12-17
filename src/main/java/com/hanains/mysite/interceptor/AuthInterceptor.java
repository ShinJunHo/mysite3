package com.hanains.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hanains.mysite.annotation.Auth;
import com.hanains.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(handler instanceof HandlerMethod == false){
			return true;
		}
		
		Auth auth =((HandlerMethod)handler).getMethodAnnotation(Auth.class);
		//dispatch handler Method 열어보기.~!
		if(auth == null){
			return true;
		}
		//달려있는 경우에만 이 작업을 해준다.
		//spring-servlet.xml의 복잡함을 덜어줌.
		//조심해야함.
		
		
		HttpSession session = request.getSession();
		
		System.out.println("AuthInterceptor.prehandler.called");
		if(session == null){
			response.sendRedirect( request.getContextPath()+"/user/loginform" );
			return false;
		}
		
		UserVo vo = (UserVo)session.getAttribute("authUser");
		
		if(vo == null){
			response.sendRedirect( request.getContextPath()+"/user/loginform" );
			return false;
		}
		

		
		
		
		return true;
	}

}
