package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.GuestbookDao;
import com.cafe24.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookDao guestbookDao;
	
	public Boolean add(GuestbookVo vo) {
		return guestbookDao.insert(vo);
		
	}

	public Boolean delete(GuestbookVo vo) {
		return guestbookDao.delete(vo);
	}
	
	public Boolean delete(Long no, String password) {
		GuestbookVo vo = new GuestbookVo();
		vo.setNo(no);
		vo.setPassword(password);
		return guestbookDao.delete(vo);
	}

	public List<GuestbookVo> getList() {
		return guestbookDao.getList();
	}

}
