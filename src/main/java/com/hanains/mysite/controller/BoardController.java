package com.hanains.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hanains.mysite.service.BoardService;
import com.hanains.mysite.vo.BoardListVo;
import com.hanains.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/list")
	public String list(Model model){
		List<BoardListVo> list =boardService.getList();
		model.addAttribute("list",list);
		return "/board/list";
	}
	
	@RequestMapping("/write")
	public String write(){
		return "/board/write";
	}
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(@ModelAttribute BoardVo vo){
		boardService.insert(vo);
		return"redirect:/board/list";
	}
	@RequestMapping("/view")
	public String view(@RequestParam("no") Long no,Model model){
		BoardVo vo=boardService.getView(no);
		model.addAttribute("board",vo);
		return "/board/view";
	}
	@RequestMapping("/modify")
	public String modify(@RequestParam("no") Long no ,Model model){
		BoardVo vo =boardService.getView(no);
		model.addAttribute("board",vo);
		return "/board/modify";
	}
	@RequestMapping("/update")
	public String update(@ModelAttribute BoardVo vo){
		boardService.update(vo);
		return "redirect:/board/list";
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam("no") Long no,@ModelAttribute BoardVo vo){
		vo.setNo(no);
		boardService.delete(vo);
		return "redirect:/board/list";
	}
}
