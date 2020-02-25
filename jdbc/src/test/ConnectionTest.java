package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

	public static void main(String[] args) {
	
		Connection conn = null;
		try {
			//1. JDBC Driver(MariaDB) �ε�
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2.�����ϱ�
			String url = "jdbc:mariadb://192.168.1.59:3307/webdb";
			conn = DriverManager.getConnection(url,"webdb","passw0rd");

			System.out.println("���� ����"+conn);
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ���� : "+e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error "+e);
		}finally {
				try {
					if(conn!=null) {
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}

}