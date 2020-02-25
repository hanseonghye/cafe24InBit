package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.CartBookVo;

public class CartBookDao {

	public Boolean insert(CartBookVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = getConnection();
			String sql = "insert into cart_book(count,book_no,cart_no) values(?, ?, ?)";
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, vo.getCount());
			psmt.setLong(2, vo.getBook_no());
			psmt.setLong(3, vo.getCart_no());
			int count = psmt.executeUpdate();
			new CartDao().updateByAddCartBook(vo.getCart_no());
			result = count == 1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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

	public List<CartBookVo> getList() {
		List<CartBookVo> result = new ArrayList<CartBookVo>();

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			String sql = "select book_no, cart_no, count from cart_book";
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();
			while (rs.next()) {
				CartBookVo vo = new CartBookVo();
				vo.setBook_no(rs.getLong(1));
				vo.setCart_no(rs.getLong(2));
				vo.setCount(rs.getInt(3));

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
