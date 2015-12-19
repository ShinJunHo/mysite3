package com.hanains.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanains.mysite.dao.BoardDao;
import com.hanains.mysite.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	private final static long PAGE_SIZE=5;
	
	private final static long PAGE_GROUP_SIZE=4;
	/*http://blog.naver.com/wldud_0729/150015317564*/
	public Map<String,Object> getBoardList(Long page,String keyword){
		
		List<BoardVo> list =boardDao.getList();
		if(page <= 0){
			page =1L;
		}
		long currentPage = page;
		long endRow=currentPage*PAGE_SIZE;
		long count =list.size();
		long number=0;
		long pageGroupCount;
		long numPageGroup;
		
		System.out.println("\ndao:"+list.size());
		if( count > 0 ){
			if (endRow > count){
				endRow = count;
			}
			//pagesize 넘기는 방법.
			list = boardDao.getSelectList(currentPage,keyword);
		}else{
			list =null;
		}
		
		/*
		 * 쓸 변수 계산은 여기서 다 하고 넘겨준다.
		 * 
		 * */
		number=count-(currentPage-1)*PAGE_SIZE;
		pageGroupCount=count/(PAGE_SIZE*PAGE_GROUP_SIZE)+(count%(PAGE_SIZE*PAGE_GROUP_SIZE)==0?0:1);
		numPageGroup = (long)Math.ceil((double)currentPage/PAGE_GROUP_SIZE);
		
		long pageCount = (count/PAGE_SIZE + (count%PAGE_SIZE == 0? 0:1));
		long startPage = PAGE_GROUP_SIZE*(numPageGroup-1)+1;
		long endPage = startPage + PAGE_GROUP_SIZE-1;
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("currentPage", currentPage);

		map.put("pageCount", pageCount);
		map.put("startPage",startPage);
		map.put("endPage",endPage);
		
		map.put("count", count);
		map.put("pageSize", PAGE_SIZE);
		map.put("pageGroupSize",PAGE_GROUP_SIZE);
		map.put("number", number);
		map.put("pageGroupCount", pageGroupCount);
		map.put("numPageGroup", numPageGroup);
		
		System.out.println(map);
		return map;
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
	public Map<String,Object> getListByKeyword(String kw){
		
		long page=1;
		List<BoardVo> list =boardDao.getList();
		if(page <= 0){
			page =1L;
		}
		long currentPage = page;
		long endRow=currentPage*PAGE_SIZE;
		long count =list.size();
		long number=0;
		long pageGroupCount;
		long numPageGroup;
		
		if( count > 0 ){
			if (endRow > count){
				endRow = count;
			}
			//pagesize 넘기는 방법.
		//	list = boardDao.getList(kw);
		}else{
			list =null;
		}
		
		/*
		 * 쓸 변수 계산은 여기서 다 하고 넘겨준다.
		 * 
		 * */
		number=count-(currentPage-1)*PAGE_SIZE;
		pageGroupCount=count/(PAGE_SIZE*PAGE_GROUP_SIZE)+(count%(PAGE_SIZE*PAGE_GROUP_SIZE)==0?0:1);
		numPageGroup = (long)Math.ceil((double)currentPage/PAGE_GROUP_SIZE);
		
		long pageCount = (count/PAGE_SIZE + (count%PAGE_SIZE == 0? 0:1));
		long startPage = PAGE_GROUP_SIZE*(numPageGroup-1)+1;
		long endPage = startPage + PAGE_GROUP_SIZE-1;
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("currentPage", currentPage);

		map.put("pageCount", pageCount);
		map.put("startPage",startPage);
		map.put("endPage",endPage);
		
		map.put("count", count);
		map.put("pageSize", PAGE_SIZE);
		map.put("pageGroupSize",PAGE_GROUP_SIZE);
		map.put("number", number);
		map.put("pageGroupCount", pageGroupCount);
		map.put("numPageGroup", numPageGroup);
		
		System.out.println(map);
		return map;
	}
}