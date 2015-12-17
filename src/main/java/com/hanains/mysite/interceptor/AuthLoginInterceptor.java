package com.hanains.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hanains.mysite.service.UserService;
import com.hanains.mysite.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println("들어왔니."+email+password);
		
		UserVo vo = new UserVo();
		
		vo.setEmail(email);
		vo.setPassword(password);
		System.out.println(vo);
		ApplicationContext applicationContext 
		= WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		UserService userService = applicationContext.getBean(UserService.class);
		
		UserVo authUser = userService.login(vo);
		System.out.println("auth"+authUser);
		//이메일 패스워드 틀린경우는.
		if(authUser == null){
			response.sendRedirect( request.getContextPath()+"/user/loginform" );
			return false;
		}
		//로그인 성공인 경우에는.
		//login처리.
		//없으면 만들어라.
		HttpSession session = request.getSession( true );
		session.setAttribute("authUser", authUser);
		System.out.println("request:"+request.getContextPath());
		response.sendRedirect(request.getContextPath());
		
		//왜 return ture;를하면 안되는거지?.
		return false;
		
	}
}
