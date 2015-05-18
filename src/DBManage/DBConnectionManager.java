package DBManage;

import java.sql.*;

public class DBConnectionManager {
	static private DBConnectionManager instance; // The single instance
	private static Connection con;
	private String user = "root";
	private String password = "123456";
	private static final String drivername = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/perforance";

	static public DBConnectionManager getInstance() {
		if (instance == null) {
			instance = new DBConnectionManager();
		}
		return instance;
	}

	public DBConnectionManager() {
		try {
			Class.forName(drivername);
		} catch (ClassNotFoundException e1) {
			System.out.println("driver出错");
		}
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("连接出错");
		}
	}

	public Connection getConnection() {
		return con;
	}
}
