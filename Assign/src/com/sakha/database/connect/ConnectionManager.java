package com.sakha.database.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	Connection con;

	public Connection getConnect() throws SQLException, ClassNotFoundException {

		Class.forName("oracle.jdbc.driver.OracleDriver");

		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "admine", "admine");

		return con;

	}

	public void closeConnect() throws SQLException {
		con.close();
	}

}
