package com.cafe24.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	
	
	public UserDao() {
		
	}
	
	public Boolean insert(UserVo vo) {
		return 1 == sqlSession.insert("user.insert", vo);
	}

	public UserVo get(String id) {
		return sqlSession.selectOne("user.getByID", id);
	}
	
	public UserVo get(String id, String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id",id); 
		map.put("password",password);
		List<UserVo> userVo = sqlSession.selectList("user.getByIDandPassword",map);
		
		if(userVo.isEmpty() == true) {
			return null;
		}
		
		return userVo.get(0);
	}
}
