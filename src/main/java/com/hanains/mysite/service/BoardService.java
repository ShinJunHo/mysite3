package com.hanains.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanains.mysite.dao.BoardDao;
import com.hanains.mysite.vo.BoardListVo;
import com.hanains.mysite.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	public List<BoardListVo> getList(){
		return boardDao.getList();
	}
	public void insert(BoardVo vo){
		boardDao.insert(vo);
		
	}
	public BoardVo getView(Long no){
		return boardDao.getView(no);
		
	}
	public void update(BoardVo vo){
		boardDao.update(vo);
		
	}
	public void delete(BoardVo vo){
		boardDao.delete(vo);
		
	}
}
