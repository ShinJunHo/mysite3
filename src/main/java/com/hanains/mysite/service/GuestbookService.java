package com.hanains.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanains.mysite.dao.GuestBookDao;
import com.hanains.mysite.vo.GuestBookVo;

@Service
public class GuestbookService {
	@Autowired
	private GuestBookDao guestbookDao;
	
	public List<GuestBookVo> getlist(){
		return guestbookDao.getList();
	}

	public void insert(GuestBookVo vo){
		guestbookDao.insert(vo);
	}
	
	public void delete(GuestBookVo vo){
		guestbookDao.delete(vo);
	}
}
