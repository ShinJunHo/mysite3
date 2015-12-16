package com.hanains.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hanains.mysite.service.BoardService;
import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/list")
	public String list(Model model){
		List<BoardVo> list =boardService.getList();
		model.addAttribute("list",list);
		return "/board/list";
	}
	//글쓰기 폼 요청.
	@RequestMapping("/write")
	public String write(HttpSession session){
		//로그인 사용자 체크
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null){
			return "redirect:/user/loginform";
		}
		return "/board/write";
	}
	// 새글 등록 요청.
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(HttpSession session,@ModelAttribute BoardVo vo){
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null ){
			return "redirect:/user/loginform";
		}
		//vo에 회원정보도 넣어줘야 겠지?.
		//자기 자신의 정보를 넣기 위해.
		vo.setMemberNo(authUser.getNo());
		boardService.insert(vo);
		return"redirect:/board/list";
	}
	
	@RequestMapping("/view")
	public String view(@RequestParam("no") Long no,Model model){
		BoardVo vo=boardService.getView(no);
		model.addAttribute("board",vo);
		return "/board/view";
	}
	//글 수정폼 요청.
	@RequestMapping("/modify")
	public String modify(HttpSession session,@RequestParam("no") Long no ,Model model){
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null){
			return "redirect:/user/loginform";
		}
		BoardVo vo =boardService.getView(no);
		model.addAttribute("board",vo);
		return "/board/modify";
	}
	//글 수정 요청.
	@RequestMapping("/update")
	public String update(HttpSession session,@ModelAttribute BoardVo vo){
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null){
			return "redirect:/user/loginform";
		}
		//vo에 회원정보도 넣어줘야 겠지?.
		//자기 자신만 수정할 수 있게.
		vo.setMemberNo(authUser.getNo());
		boardService.update(vo);
		return "redirect:/board/list";
	}
	@RequestMapping("/delete")
	public String delete(HttpSession session,@RequestParam("no") Long no,@ModelAttribute BoardVo vo){
		//로그인 사용자 체크
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null){
			return "redirect:/user/loginform";
		}
		//vo에 회원정보도 넣어줘야 겠지?.
		//글쓴이만 삭제 할 수 있게.
		vo.setMemberNo(authUser.getNo());
		vo.setNo(no);
		boardService.delete(vo);
		return "redirect:/board/list";
	}
}
