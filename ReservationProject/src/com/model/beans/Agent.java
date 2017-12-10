
package com.model.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Agent implements Serializable {
	private String username;
	private String password;
	private String email;
	private String nom;
	private String prenom;

	public Agent() {

	}

	public Agent(String username, String password, String email, String nom, String prenom) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.nom = nom;
		this.prenom = prenom;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Function Authentification
	 * 
	 * @param username
	 * @param password
	 * @return boolean
	 */
	public boolean isAgentr(String username, String password) {

		Connection conn = Conn.getConnection();
		boolean isAgent = false;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("select * from agent where username =? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet res = ps.executeQuery();
			if (res.next()) {
				isAgent = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return isAgent;
	}

	/**
	 * Get Agent From Databases With Identifier
	 * 
	 * @param username
	 * @return Agent
	 */
	
	public Agent getAgent(String username) {
		Connection conn = Conn.getConnection();
		Agent user = new Agent();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from Agent where username=?");
			ps.setString(1, username);
			ResultSet res = ps.executeQuery();
			if (res.next()) {
				user.setUsername(res.getString("username"));
				user.setPassword(res.getString("password"));
				user.setNom(res.getString("nom"));
				user.setPrenom(res.getString("prenom"));
				user.setEmail(res.getString("email"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;

	}
}