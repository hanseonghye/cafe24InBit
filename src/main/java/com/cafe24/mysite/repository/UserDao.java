package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private DataSource datasource;
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserDao() {
	}
	
	public UserVo get(Long no) throws UserDaoException {
		return sqlSession.selectOne("user.getByNo",no);
	}

	public UserVo get(String email, String password) throws UserDaoException{
		Map<String, String> map = new HashMap<String,String>();
		map.put("email",email);
		map.put("password",password);
		UserVo userVo = sqlSession.selectOne("user.getByEmailAndPassword",map);
		return userVo;
	}

	public Boolean insert(UserVo vo) {
		System.out.println(vo);
		int count = sqlSession.insert("user.insert",vo);
		System.out.println(vo);
		return 1 == count;
	}

	
	public Boolean update(UserVo vo,String pre_email) {
		return update(vo.getName(),vo.getEmail(),vo.getPassword(),vo.getGender(),pre_email);
	}

	public Boolean update(String name, String email, String password, String gender, String pre_email) {
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = datasource.getConnection();

			String sql = "update user set name=?, email=?, password=?, gender=? where email=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2,email);
			psmt.setString(3, password);
			psmt.setString(4, gender);
			psmt.setString(5, pre_email);
			int count = psmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}
}
