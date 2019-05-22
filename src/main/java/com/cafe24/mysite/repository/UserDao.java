package com.cafe24.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	public UserDao() {
	}

//	public UserVo get(Long no) throws UserDaoException {
//		return sqlSession.selectOne("user.getByNo",no);
//	}

	public UserVo get(String email, String password) throws UserDaoException {

		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("password", password);
		List<UserVo> userVo = sqlSession.selectList("user.getByEmailAndPassword", map);

		if (userVo.isEmpty() == true) {
			return null;
		}

		return userVo.get(0);
	}

	public UserVo get(String email) {
		return sqlSession.selectOne("user.getByEmail", email);
	}

	public Boolean insert(UserVo vo) {
		return 1 == sqlSession.insert("user.insert", vo);
	}

	public Boolean update(UserVo vo) {
		return 1 == sqlSession.update("user.update", vo);
	}

}
