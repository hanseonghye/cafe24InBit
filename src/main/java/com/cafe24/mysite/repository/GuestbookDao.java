package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private DataSource datasource;
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean delete(GuestbookVo vo) {
		
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			// 1. JDBC Driver(MariaDB) 로딩
			conn =datasource.getConnection();

			String sql = "delete from guestbook where no=? and password=?";
			psmt = conn.prepareStatement(sql);
			psmt.setLong(1, vo.getNo());
			psmt.setString(2, vo.getPassword());
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
	
	
	public Boolean insert(GuestbookVo vo) {
	
		boolean result = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			// 1. JDBC Driver(MariaDB) 로딩
			conn = datasource.getConnection();

			String sql = "insert into guestbook values(null,?,?,?, now())";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getPassword());
			psmt.setString(3, vo.getContents());
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
	public List<GuestbookVo> getList(){
		List<GuestbookVo> result = sqlSession.selectList("guestbook.getlist");
		return result;
	}	
	
}
