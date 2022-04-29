package idv.ash.model.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	final static private String URL = "jdbc:mysql://localhost:#port/#dataBase?serverTimezone=UTC&useSSL=false";
	final static private String USER = "user";
	final static private String PASSWORD = "password";
	static Connection conn = setDefaultConnection(URL,USER,PASSWORD);

	private static Connection setDefaultConnection(String url, String user, String password) {

		if (conn == null) {
			try {
				openConnect(URL,USER,PASSWORD);
			} catch (Exception e) {
				System.out.println("連線異常");
				e.printStackTrace();
			}

		}
		return conn;
	}

	public  void createQuery(String sql) {
		ResultSet resultSet = null;

		Statement statement;
		try {
			statement = conn.createStatement();
			String selectSql = sql;
			resultSet = statement.executeQuery(selectSql);
			while (resultSet.next()) {
				System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

	}

	public static void closeConnection() {
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void openConnect() {

		openConnect(URL, USER, PASSWORD);
	}

	public static void openConnect(String url, String user, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("連線異常");
			e.printStackTrace();
		}

	}

}
