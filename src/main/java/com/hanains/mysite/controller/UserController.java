package com.hanains.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanains.mysite.service.UserService;
import com.hanains.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	//얘가 서비스를 사용하는거.
	@Autowired
	private UserService userService;
	
	//URL을 메소드별로 Pretty URL을 뽑아낼 수 있도록 노력해야한다.
	//이렇게 중요하다. 전체적인 Architecture을 잡는거니깐.
	@RequestMapping("/joinform")
	public String joinform(){
		System.out.println("Hello world");
		return "/user/joinform";
	}
	
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo vo){
		
		userService.join(vo);
		
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess(){
	
		return "/user/joinsuccess";
	}
	@RequestMapping("/loginform")
	public String loginform(){
	
		return "/user/loginform";
	}
	/*
	@RequestMapping("/login")
	public String login(HttpSession session, @ModelAttribute UserVo vo){
		
		UserVo authUser =userService.login(vo);
		//아이디와 패스워드 만들어서 리턴 해줘야 한다.
		//여기서 로그인 처리를 해줘야겠지.
		session.setAttribute("authUser",authUser);
		
		//login 후  main 으로 가는거니깐.
		return "redirect:/";
	}*/
	/*
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		
		session.removeAttribute("authUser");
		session.invalidate();
		//intercept가 대신 해주게 되다 .
		//Exception은컨트롤러기
		return "redirect:/";
	}*/
	
}
