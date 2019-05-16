package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	public Boolean delete(GuestbookVo vo) {
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			// 1. JDBC Driver(MariaDB) 로딩
			conn = getConnection();

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
			conn = getConnection();

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
		List<GuestbookVo> result = new ArrayList<GuestbookVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			String sql = "select no, name, contents, date_format(reg_date, '%Y-%m-%d %h:%i:%s') from guestbook order by reg_date desc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String contents = rs.getString(3);
				String regDate = rs.getString(4);	
				
				GuestbookVo vo = new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setContents(contents);
				vo.setRegDate(regDate);
				
				result.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if( rs != null ) {
					rs.close();
				}
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
 			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return result;
	}	
	
	
	private Connection getConnection() throws SQLException {

		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.1.35:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("connection err");
		}
		return conn;
	}
}
