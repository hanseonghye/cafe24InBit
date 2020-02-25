package hr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
	public List<EmployeeVo> getList(String keyword) {
		List<EmployeeVo> employee = new ArrayList<EmployeeVo>();
		Connection conn = null;
		ResultSet rs = null;

		PreparedStatement pstmt = null;
		try {
			// 1. JDBC Driver(MariaDB) �ε�
			Class.forName("org.mariadb.jdbc.Driver");

			// 2.�����ϱ�
			String url = "jdbc:mariadb://192.168.1.59:3307/employees";
			conn = DriverManager.getConnection(url, "webdb", "passw0rd");

			// 3. sql�� ����
			String sql = "select emp_no, first_name, last_name, hire_date" + " from employees"
					+ " where first_name like ?" + " or last_name like ?";
			pstmt = conn.prepareStatement(sql);

			// 4. ���ε�
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");

			// 5. ���� ����
			rs = pstmt.executeQuery();

			// 5. ��� ��������
			while (rs.next()) {
				Long no = rs.getLong(1); // 1���� �����Ѵ�.
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String hireDate = rs.getString(4);
				EmployeeVo vo = new EmployeeVo();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setHireDate(hireDate);
				employee.add(vo);
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

				if (pstmt != null) {
					pstmt.close();
				}

				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return employee;
	}
}