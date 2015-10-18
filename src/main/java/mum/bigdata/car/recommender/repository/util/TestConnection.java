package mum.bigdata.car.recommender.repository.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Be sure the JavaDb server is running and that derbyclient.jar has been added
 * as an external jar to this project.
 *
 */
public class TestConnection {
	HiveConnectionManager cm = HiveConnectionManager.getInstance();
	private static final String readquery = "SELECT * FROM geolocation";

	public void testConnection() throws SQLException {
		Connection con = cm.getConnection();
		Statement stmt = con.createStatement();
		System.out.println("the read query: " + readquery);

		// Perform SELECT
		ResultSet rs = stmt.executeQuery(readquery);
		while (rs.next()) {
			String id = rs.getString("geolocation.truckid").trim();
			String city = rs.getString("geolocation.city").trim();
			System.out.println("id = " + id + " city = " + city);
		}
		// close Statement object; do not re-use
		stmt.close();
		/*
		 * //Perform INSERT stmt = con.createStatement();
		 * 
		 * //INSERT will store the auto-generated id key created during
		 * insertion //can get this with a call to getGeneratedKeys()
		 * stmt.executeUpdate(insertstmt, Statement.RETURN_GENERATED_KEYS); rs =
		 * stmt.getGeneratedKeys(); int newId = -1; if (rs.next()) { newId =
		 * rs.getInt(1); System.out.println("Insert successful. New row has id "
		 * + newId); } else{ System.out.println(
		 * "Insert unsuccessful. No new row has been added."); }
		 */

		// Lets the ConnectManager handle closing of connection.
		cm.closeConnection(con);
	}

	public static void main(String[] args) {
		TestConnection tc = new TestConnection();
		try {
			tc.testConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
