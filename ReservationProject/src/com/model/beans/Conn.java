/*
* @authors Houssem, Bassem
* 
*/

package com.model.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conn implements Serializable {

	private static final long serialVersionUID = 1L;
	public static String dbName = "DBReservation", password = "houssem", username = "postgres", port = "5432",
			location = "localhost";
	private static PreparedStatement preparedStatement = null;

	private static Connection conn;

	static {
		try {
			Class.forName("org.postgresql.Driver");
			//
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBReservation", "postgres", "houssem");
			System.out.println("connected");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("non connected");
		}
	}

	public static Connection getConnection() {
		return conn;
	}

	public static PreparedStatement getPreparedStatement() {
		return preparedStatement;

	}

	public static PreparedStatement prepareStatement(String query) {
		try {
			preparedStatement = getConnection().prepareStatement(query);
		} catch (SQLException ex) {
			Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
		return preparedStatement;
	}

	public static ResultSet executPreparedStatement() {
		try {
			return preparedStatement.executeQuery();
		} catch (SQLException ex) {
			Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public static int executUpdatePreparedStatement() {
		try {
			System.out.println(preparedStatement.toString());
			return preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
	}

}
