package com.hanains.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	public String index(){
		//return "/WEB-INF/views/main/index.jsp";
		//ViewResolver를 통해서 간략하게 줄어듬.
		return "/main/index";
	}
	
}
