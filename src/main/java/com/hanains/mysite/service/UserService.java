package com.hanains.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanains.mysite.dao.UserDao;
import com.hanains.mysite.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	
	
	public void join(UserVo vo){
		userDao.insert(vo);
		//dao test도 해보고 
		//그렇게 해야 하는데 지금은 컨트롤 중심으로 구현을 하고 있는것이다.
	}
	
	
	public UserVo login(UserVo vo){
	//	UserVo authUser = userDao.get(vo.getEmail(), vo.getPassword());
		UserVo authUser = userDao.get(vo);
		
		System.out.println("login authUser"+authUser);
		//toString override했으니깐.
		return authUser;
		//이렇게 하면 서비스가 됨.
	}
	public UserVo getUser(String email){
		UserVo vo = userDao.get(email);
		return vo;
	}
}
