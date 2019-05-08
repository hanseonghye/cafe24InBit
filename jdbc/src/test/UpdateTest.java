package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest {
	public static void main(String[] args) {
		boolean result = update(1L,"개발팀");
		if( result ) {
			System.out.println("업데이트 성공");
		}
	}
	
	public static boolean update(Long no,String name) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try {
			// 1. JDBC Driver(MariaDB) 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2.연결하기
			String url = "jdbc:mariadb://192.168.1.59:3307/webdb";
			conn = DriverManager.getConnection(url,"webdb","passw0rd");

			// 3. statement 객체 생성
			stmt = conn.createStatement();

			// 4. sql문 실행
			String sql = "update departments"
							+ " set name='"+name+"'"
							+ " where no="+no;
			System.out.println(sql);
			int count = stmt.executeUpdate(sql);
			
			//5. 결과 가져오기
			result = count ==1;

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error " + e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				
				if(stmt != null) {
					stmt.close();
				}
				
				if(rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
