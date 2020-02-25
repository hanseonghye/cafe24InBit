package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.OrderBookVo;


public class OrderBookDao {
	
	
	public Boolean insert(OrderBookVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = getConnection();
			String sql = "insert into order_book(count,book_no,order_no) values(?, ?, ?)";
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, vo.getCount());
			psmt.setLong(2, vo.getBook_no());
			psmt.setLong(3, vo.getOrder_no());
			int count = psmt.executeUpdate();
			new OrderDao().updateByAddOrderBook(vo.getOrder_no());
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

	
	public Boolean _insert(OrderBookVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = getConnection();
			String sql = "insert into order_book(count,book_no,order_no) values(?, ?, ?)";
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, vo.getCount());
			psmt.setLong(2, vo.getBook_no());
			psmt.setLong(3, vo.getOrder_no());
			int count = psmt.executeUpdate();
			new OrderDao().updateByAddOrderBook(vo.getOrder_no());
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

	public List<OrderBookVo> getList() {
		List<OrderBookVo> result = new ArrayList<OrderBookVo>();

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			String sql =
				"select ob.book_no, ob.order_no, ob.count, b.title" + 
				"	from order_book ob, book b" + 
				"    where ob.book_no = b.no";
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();
			while (rs.next()) {
				OrderBookVo vo = new OrderBookVo();
				vo.setBook_no(rs.getLong(1));
				vo.setOrder_no(rs.getLong(2));
				vo.setCount(rs.getInt(3));
				vo.setBook_title(rs.getString(4));

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
