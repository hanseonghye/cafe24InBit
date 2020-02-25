package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.OrderVo;

public class OrderDao {

	public Boolean insert(OrderVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = getConnection();
			String sql = "insert into orders(no,member_no,price,date,address_no) values(null, ?,?,?,?)"; //member_no , price, date, address_no
			psmt = conn.prepareStatement(sql);
			psmt.setLong(1, vo.getMember_no());
			psmt.setInt(2, vo.getPrice());
			psmt.setTimestamp(3, getCurrentTimeStamp());
			psmt.setLong(4, vo.getAddress_no());
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public Boolean updateByAddOrderBook(Long order_no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = getConnection();
			String sql = "update orders o set price = (select sum(b.price*ob.count) from order_book ob, book b where ob.order_no =? and ob.book_no=b.no) where o.no=?";
			psmt = conn.prepareStatement(sql);

			psmt.setLong(1, order_no);
			psmt.setLong(2, order_no);
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

	public List<OrderVo> getList() {
		List<OrderVo> result = new ArrayList<OrderVo>();

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			String sql =
				"select o.no, o.price, o.date, o.member_no, o.address_no,  m.name, m.email, ad.address" + 
				"	from orders o, member m, addresses ad" + 
				"    where o.member_no = m.no" + 
				"		and m.no = ad.member_no" + 
				"        and ad.no = o.address_no";
			
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				OrderVo vo = new OrderVo();
				vo.setNo(rs.getLong(1));
				vo.setPrice(rs.getInt(2));
				vo.setDate(rs.getTimestamp(3));
				vo.setMember_no(rs.getLong(4));
				vo.setAddress_no(rs.getLong(5));
				vo.setMember_name(rs.getString(6));
				vo.setMember_email(rs.getString(7));
				vo.setAddress(rs.getString(8));

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
	
	private static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}
	
}
