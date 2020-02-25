package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTest {
	public static void main(String[] args) {
		boolean result = insert("��������");
		insert("�繫��");
		if ( result ) {
			System.out.println("�Է� ����");
		}
	}
	
	public static boolean insert(String name) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try {
			// 1. JDBC Driver(MariaDB) �ε�
			Class.forName("org.mariadb.jdbc.Driver");

			// 2.�����ϱ�
			String url = "jdbc:mariadb://192.168.1.59:3307/webdb";
			conn = DriverManager.getConnection(url,"webdb","passw0rd");

			// 3. statement ��ü ����
			stmt = conn.createStatement();

			// 4. sql�� ����
			String sql = "insert into departments values(null, '"+name+"')";
			int count = stmt.executeUpdate(sql);
			
			//5. ��� ��������
			result = count ==1;

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