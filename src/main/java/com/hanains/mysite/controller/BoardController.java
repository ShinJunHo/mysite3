package com.hanains.mysite.controller;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hanains.mysite.annotation.Auth;
import com.hanains.mysite.annotation.AuthUser;
import com.hanains.mysite.service.BoardService;
import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	private static final String SAVE_PATH = "/temp/";
	
	@RequestMapping("/list")
	public String list(	@RequestParam( value="keyword", required=true, defaultValue="" )String keyword,
						@RequestParam( value="page", required=true, defaultValue="1" )Long page,
					Model model){
		System.out.println("\npage:"+page+"keyword:"+keyword);
		Map<String,Object> list =boardService.getBoardList(page,keyword);
		System.out.println("\nlist"+list);
		model.addAttribute("list",list);
		model.addAttribute("keyword",keyword);
		model.addAttribute("page",page);
		
		return "/board/list";
	}
	//글쓰기 폼 요청.
	@Auth
	@RequestMapping("/write")
	public String write(){
		/* 인터셉터가 처리를 해주니깐 
		//로그인 사용자 체크
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null){
			return "redirect:/user/loginform";
		}
		*/
		return "/board/write";
	}
	// 새글 등록 요청.
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(HttpSession session,
						@RequestParam("uploadFile") MultipartFile multipartFile,
						@ModelAttribute BoardVo vo,
						Model model){
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null ){
			return "redirect:/user/loginform";
		}
		//이건 세션을 땔 수 없어 Auth USer의 NO값을 확인해야하는데,
		//기술 침투를 막는건데 얘는 가만히 냅둬야..
		//vo에 회원정보도 넣어줘야 겠지?.
		//자기 자신의 정보를 넣기 위해.
		//vo.setMemberName(authUser.getName()); 
		
		if(vo.getTitle().trim().length() == 0 || vo.getContent().trim().length() == 0 ){
			return "redirect:/board/list?result=fail";
		}
		
		//파일처리
		if(multipartFile.isEmpty() == false ){
			String fileOriginalName = multipartFile.getOriginalFilename();
			String extName = fileOriginalName.substring(fileOriginalName.lastIndexOf(".")+1,
														fileOriginalName.length());
			String fileName = multipartFile.getName();
			Long size = multipartFile.getSize();
			
			String saveFileName = genSaveFileName(extName);
			
			System.out.println(" ######## fileOriginalName : " + fileOriginalName);
			System.out.println(" ######## fileName : " + fileName);
			System.out.println(" ######## fileSize : " + size);
			System.out.println(" ######## fileExtensionName : " + extName);
			System.out.println(" ######## saveFileName : " + saveFileName);
			
			writeFile(multipartFile,SAVE_PATH,saveFileName);
			
			String url = "/profile-images/" + saveFileName;
			vo.setFileName(url);
			model.addAttribute("profileUrl", url);
			
		}else{
			vo.setFileName("");
		}
		vo.setMemberNo(authUser.getNo());
		boardService.insert(vo);
		return"redirect:/board/list";
	}
	
	
	private void writeFile(MultipartFile file, String path, String fileName){
	
		FileOutputStream fos = null;
		try{
			byte fileData[] = file.getBytes();
			fos = new FileOutputStream(path+fileName);
			fos.write(fileData);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if( fos != null){
				try{
					fos.close();
				}catch(Exception e){
					
				}
			}
		}
		
		
		
		
	}
	private String genSaveFileName(String extName){
		Calendar calendar = Calendar.getInstance();
		String fileName="";
		
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName +=("."+extName);
		
		return fileName;
		
	}
	
	
	
	
	
	
	@RequestMapping("/view/{no}")
	public String view(@PathVariable("no")Long no,Model model){
		BoardVo vo=boardService.getView(no);
		model.addAttribute("board",vo);
		return "/board/view";
	}
	//글 수정폼 요청.
	@Auth
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
	
	
	@Auth
	@RequestMapping("/update")
	public String update(@AuthUser UserVo authUser,
						@RequestParam("uploadFile") MultipartFile multipartFile,
						@ModelAttribute BoardVo vo){
		
		
		if(vo.getTitle().trim().length() == 0 || vo.getContent().trim().length() == 0 ){
			return "redirect:/board/list?result=fail";
		}
		
		//파일처리
		if(multipartFile.isEmpty() == false ){
			String fileOriginalName = multipartFile.getOriginalFilename();
			String extName = fileOriginalName.substring(fileOriginalName.lastIndexOf(".")+1,
														fileOriginalName.length());
			String fileName = multipartFile.getName();
			Long size = multipartFile.getSize();
			
			String saveFileName = genSaveFileName(extName);
			
			System.out.println(" ######## fileOriginalName : " + fileOriginalName);
			System.out.println(" ######## fileName : " + fileName);
			System.out.println(" ######## fileSize : " + size);
			System.out.println(" ######## fileExtensionName : " + extName);
			System.out.println(" ######## saveFileName : " + saveFileName);
			
			writeFile(multipartFile,SAVE_PATH,saveFileName);
			
			String url = "/profile-images/" + saveFileName;
			vo.setFileName(url);
			
		}else{
			vo.setFileName("");
		}
		
		vo.setMemberNo(authUser.getNo());
		System.out.println("\n\nfileName:"+ vo.getFileName());
		boardService.update(vo);
		return "redirect:/board/list";
	}
	//글 수정 요청.
	/*@Auth
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
	*/@Auth
	@RequestMapping("/delete/{no}")
	public String delete(HttpSession session,@PathVariable("no")Long no,@ModelAttribute BoardVo vo){
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
	
	///////////relpy
	@RequestMapping("/reply")
	public String reply(HttpSession session,@RequestParam("no") Long no,Model model ){
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null){
			return "redirect:/user/loginform";
		}
		BoardVo vo=boardService.getView(no);
		model.addAttribute("board",vo);
		
		return "/board/write";
		
	}

}