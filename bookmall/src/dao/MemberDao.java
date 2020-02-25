package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.MemberVo;

public class MemberDao {
	public Boolean insert(MemberVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			// 1. JDBC Driver(MariaDB) 로딩
			conn = getConnection();

			String sql = "insert into member values(null, ?, ?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getEmail());
			psmt.setString(3, vo.getPw());
			psmt.setString(4, vo.getPhone());
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

	public List<MemberVo> getList() {
		List<MemberVo> result = new ArrayList<MemberVo>();

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			String sql = "select no, name, email, pw, phone from member";
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();
			while (rs.next()) {
				MemberVo vo = new MemberVo();
				vo.setNo(rs.getLong(1));
				vo.setName(rs.getString(2));
				vo.setEmail(rs.getString(3));
				vo.setPw(rs.getString(4));
				vo.setPhone(rs.getString(5));

				result.add(vo);
			}

		}  catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
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
			String url = "jdbc:mariadb://192.168.1.27:3307/bookmall";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("connection err");
		}
		return conn;
	}
}
