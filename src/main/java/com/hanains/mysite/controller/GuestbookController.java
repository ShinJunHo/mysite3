package com.hanains.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanains.mysite.dao.GuestBookDao;
import com.hanains.mysite.service.GuestbookService;
import com.hanains.mysite.vo.GuestBookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	private  GuestbookService guestbookService;
	
	@RequestMapping("/list")
	public String list(Model model){
		List<GuestBookVo> list =guestbookService.getlist();
		model.addAttribute("list",list);
		return "/guestbook/list";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String add(@ModelAttribute GuestBookVo vo){
//		settter getter 할때는 파라미터 네임이 같아야 한다.
		// jsp 의 name="값" 과 VO의 필드네임이 같아야 한다는 말이다.
		if(vo.getMessage().trim().length()==0||vo.getName().trim().length()==0||vo.getPassword().trim().length()==0){
			return "redirect:/guestbook/list?result=fail";
		}
		guestbookService.insert(vo);
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(@ModelAttribute GuestBookVo vo){
		guestbookService.delete(vo);
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping("/deleteform")
	public String deleteform(){
		return "/guestbook/deleteform";
	}
}
