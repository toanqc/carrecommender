package mum.bigdata.car.recommender.repository.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.bigdata.car.recommender.util.PropertiesUtil;

/**
 * @author Toan Quach
 */
public class HiveConnectionManager {

	private static final String DATABASE = "hive.url";
	private static final String USERNAME = "hive.username";
	private static final String PASSWORD = "hive.password";
	private static final String HIVE_CONFIG = "hive.properties";

	private String databaseUrl;
	private String user;
	private String pass;

	private Connection conn = null;
	private static HiveConnectionManager instance = null;

	private HiveConnectionManager() {
		Properties props = PropertiesUtil.getProperties(HIVE_CONFIG);
		databaseUrl = props.getProperty(DATABASE);
		user = props.getProperty(USERNAME);
		pass = props.getProperty(PASSWORD);
	}

	public static HiveConnectionManager getInstance() {
		if (instance == null) {
			instance = new HiveConnectionManager();
		}
		return instance;
	}

	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(databaseUrl, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
