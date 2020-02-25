package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.CartVo;

public class CartDao {
	public Boolean insert(CartVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = getConnection();
			String sql = "insert into cart(no,member_no,price) values(null, ?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setLong(1, vo.getMember_no());
			psmt.setInt(2, vo.getPrice());
			int count = psmt.executeUpdate();
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

	public List<CartVo> getList() {
		List<CartVo> result = new ArrayList<CartVo>();

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			String sql = "select c.no, c.member_no, b.title, sum(cb.count), sum(b.price*cb.count)" + 
					"	from cart c, cart_book cb, book b" + 
					"		where c.no = cb.cart_no" + 
					"			and cb.book_no = b.no" + 
					"		group by b.no;";
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();
			while (rs.next()) {
				CartVo vo = new CartVo();
				vo.setNo(rs.getLong(1));
				vo.setMember_no(rs.getLong(2));
				vo.setTitle(rs.getString(3));
				vo.setCount(rs.getInt(4));
				vo.setPrice(rs.getInt(5));

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

	public Boolean updateByAddCartBook(Long cart_no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = getConnection();
			String sql = "update cart c set price = (select sum(b.price*cb.count) from cart_book cb, book b where cb.cart_no =? and cb.book_no = b.no);";
			psmt = conn.prepareStatement(sql);

			psmt.setLong(1, cart_no);
			int count = psmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
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
				e.printStackTrace();
			}
		}
		return result;
	}
}
