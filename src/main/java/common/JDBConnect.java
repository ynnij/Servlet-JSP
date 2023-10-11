package common;

import java.sql.Connection;
import java.sql.DriverManager;

import jakarta.servlet.ServletContext;

public class JDBConnect {
	private Connection con;
	
	public JDBConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/musthave";
			String id ="musthave";
			String pwd = "tiger";
			con = DriverManager.getConnection(url,id,pwd);
			
			System.out.println("DB 연결 성공(기본 생성자)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public JDBConnect(String driver, String url, String id, String pwd) {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,id,pwd);
			System.out.println("DB 연결 성공(인수 생성자 1)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public JDBConnect(ServletContext application) {
		try {
			String driver = application.getInitParameter("MySqlDriver");
			Class.forName(driver);
			
			String url = application.getInitParameter("MySqlURL");
			String id = application.getInitParameter("MySqlId");;
			String pwd = application.getInitParameter("MySqlPwd");
			
			con = DriverManager.getConnection(url,id,pwd);
			System.out.println("DB 연결 성공(인수 생성자 2)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		return this.con;
	}
	
	public void close() {
		try {
			if(con!=null) con.close();
			
			System.out.println("JDBC 자원 해제");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
