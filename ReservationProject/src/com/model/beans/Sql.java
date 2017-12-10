/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Houssem & Bassem -dz
 */

public class Sql {
	public static String dbName = "DBReservation", password = "houssem", username = "postgres", port = "5432",
			location = "localhost";
	private static Connection connection = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;

	public static Connection getConnection() {
		try {

			if (connection == null || connection.isClosed()) {
				initConnection();
				return connection;
			} else {
				return connection;
			}

		} catch (SQLException ex) {
			Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
		}
		return connection;
	}

	public static Connection initConnection() {

		try {
			if (connection == null) {
				connection = DriverManager.getConnection("jdbc:postgresql://" + location + ":" + port + "/" + dbName,
						username, password);
			}
		} catch (SQLException ex) {
			Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
		}
		return connection;
	}

	public static void setConnection(Connection connect) {
		connection = connect;
	}

	public static ResultSet select(String query) throws SQLException {

		if (connection != null && statement != null) {
			ResultSet result = statement.executeQuery(query);
			return result;
		}
		return null;
	}

	public static void createStatement() {
		try {
			statement = connection.createStatement();
		} catch (SQLException ex) {
			Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static int execut(String query) throws SQLException {
		if (connection != null && statement != null) {
			statement = connection.createStatement();
			return statement.executeUpdate(query);
		}
		return -1;
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
