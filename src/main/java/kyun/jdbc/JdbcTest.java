package kyun.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {
	
	public static void main(String[] args) {
        /* HSQL */
        String className = "org.hsqldb.jdbc.JDBCDriver";
        String url = "jdbc:hsqldb:mem:testdb";
        String user = "sa";
        String passwd = "";
		
		/* JDBC */
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		/* SQL */
		String sql = null;

		try {
			//1. JDBC Driver Loading
			Class.forName(className);
			
            //2. DB Connection 얻어오기
			con = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connection ok!!!");
			
            //3. Statement 객체 생성 : DB가 수행할 SQL문을 전송하는 역할
			stmt = con.createStatement();
			
            //4. create table
            sql = "CREATE CACHED TABLE employee (id INTEGER IDENTITY, name VARCHAR(100), phone VARCHAR(100))";
            stmt.executeUpdate(sql);

            //5. insert
            sql = "insert into employee values (?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, 100);
            pstmt.setString(2, "NHN");
            pstmt.setString(3, "분당");
            int count = pstmt.executeUpdate();
            System.out.println("insert count=" + count);

            //5. select
            sql = "select * from employee where id = " + 100;
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println("id : " + rs.getString(1));
                System.out.println("name : " + rs.getString("name"));
                System.out.println("phone : " + rs.getString(3));
				System.out.println("--------------------------");
			}
			
            sql = "select * from employee where id = ?";
			pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, 100);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("id : " + rs.getString(1));
                System.out.println("name : " + rs.getString("name"));
                System.out.println("phone : " + rs.getString(3));
				System.out.println("--------------------------");
			}
		} catch (ClassNotFoundException ex) {
			System.out.println("Driver loading is failed");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
            //모든 자원을 반납한다.
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
	}
}
