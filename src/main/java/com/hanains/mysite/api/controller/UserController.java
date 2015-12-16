package com.hanains.mysite.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanains.mysite.service.UserService;
import com.hanains.mysite.vo.UserVo;

@Controller("userApiController")
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello(){
		return "안녕하세요.";
	}
	@ResponseBody
	@RequestMapping("/json")
	public Object json(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result","Success"); // success or fail.
		
		map.put("message",null); //실패시 실패 메시지 전송.
		
		//map.put("data",new Boolean(true)); // 성공시 데이터 전송.
		
		//map.put("data",vo == null); // 성공시 데이터 전송.
		
		return map;
	}
	@ResponseBody
	@RequestMapping("/checkemail")
	public Object checkEmail(@RequestParam(value="email",required=true,defaultValue="") String email){
		UserVo vo = userService.getUser(email);
		
		//null 이면 사용해도 되는거고 true null 이 아니면 있는거니깐 사용 못하고, 
		//이런게 TDD 논리를 새워놓고 error를 고쳐가면서 해결해 나가는것.
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result","Success"); // success or fail.
		map.put("message",null); //실패시 실패 메시지 전송.
		map.put("data",vo == null); // 성공시 데이터 전송.
		return map;
		
	}
}
