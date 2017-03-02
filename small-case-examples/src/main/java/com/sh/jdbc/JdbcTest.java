package com.sh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {

	public void queryFromDB() {
		try {
			// 加载MySql的驱动类
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		}
		// 连接MySql数据库，用户名和密码都是root
		String url = "jdbc:mysql://localhost:3306/hzc?createDatabaseIfNotExist=true&zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8";
		String username = "root";
		String password = "1Qazxcvbn";
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM tblpricecode WHERE IsActive=1 limit 10");
			while (rs.next()) {
				String carGroupCode = rs.getString("eds_id");
				String pass = rs.getString(1); // 此方法比较高效
				System.out.println(carGroupCode);
				System.out.println(pass);
			}

			if (rs != null) { // 关闭记录集
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) { // 关闭声明
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) { // 关闭连接对象
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
	}
}
