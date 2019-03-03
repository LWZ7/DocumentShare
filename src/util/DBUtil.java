package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	static String ip = "127.0.0.1";
	static int port = 3306;
	static String database = "doc";
	static String encoding = "UTF-8";
	static String loginName = "root";
	static String password = "7319488453622l";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
		//System.out.println(url);
		return DriverManager.getConnection(url, loginName, password);
	}
}
