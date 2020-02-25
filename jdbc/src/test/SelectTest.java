package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {
	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC Driver(MariaDB) �ε�
			Class.forName("org.mariadb.jdbc.Driver");

			// 2.�����ϱ�
			String url = "jdbc:mariadb://192.168.1.59:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "passw0rd");

			// 3. statement ��ü ����
			stmt = conn.createStatement();

			// 4. sql�� ����
			String sql = "select no, name from departments";
			rs = stmt.executeQuery(sql);

			// 5. ��� ��������
			while (rs.next()) {
				Long no = rs.getLong(1); // 1���� �����Ѵ�.
				String name = rs.getString(1);
				System.out.println(no + " " + name);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ���� : " + e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error " + e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (stmt != null) {
					stmt.close();
				}

				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}