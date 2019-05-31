package com.cafe24.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.UserDao;
import com.cafe24.mysite.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public Boolean join (UserVo vo) {
		return userDao.insert(vo);
	}

	public UserVo getUser(String id, String password) {
		return userDao.get(id, password);
	}

	public Boolean existID(String id) {
		UserVo vo =  userDao.get(id);
		return vo != null;
	}
}
