
package com.model.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Agents {

	public Agent initAgent(int id) {
		Agent J = null;
		try {
			Sql.prepareStatement("select * from agent where id =? ");
			Sql.getPreparedStatement().setInt(1, id);
			ResultSet res = Sql.executPreparedStatement();
			if (res.next()) {
				J = new Agent(res.getString("username"), res.getString("password"), res.getString("email"),
						res.getString("nom"), res.getString("prenom"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return J;
	}

	public int insertAgent(Agent agent) {
		try {
			Sql.prepareStatement("select * from agent where username = ?");
			Sql.getPreparedStatement().setString(1, agent.getUsername());
			ResultSet result = Sql.executPreparedStatement();
			if (!result.next()) {
				Sql.prepareStatement("insert into agent values(?,?,?,?,?)");
				Sql.getPreparedStatement().setString(1, agent.getUsername());
				Sql.getPreparedStatement().setString(2, agent.getPassword());
				Sql.getPreparedStatement().setString(3,agent.getNom());
				Sql.getPreparedStatement().setString(4, agent.getPrenom());
				Sql.getPreparedStatement().setString(5,agent.getEmail());
				Sql.executUpdatePreparedStatement();
			} else {
				return -1;
			}
		} catch (SQLException ex) {
			Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
		return 1;
	}

	public int updateAgent(Agent c) {
		try {
			Sql.prepareStatement("update agent set username = ? , password = ? "
					+ " , nom=? , prenom =? ,email =? where username = ?");
			Sql.getPreparedStatement().setString(1, c.getUsername());
			Sql.getPreparedStatement().setString(2, c.getPassword());
			Sql.getPreparedStatement().setString(3, c.getNom());
			Sql.getPreparedStatement().setString(4, c.getPrenom());
			Sql.getPreparedStatement().setString(5, c.getEmail());
			Sql.executUpdatePreparedStatement();
		} catch (SQLException ex) {
			Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
		return 1;
	}

	public int deleteAgent(String username) {
		try {
			Sql.prepareStatement("delete from agent where username = ?");
			Sql.getPreparedStatement().setString(1, username);
			Sql.executUpdatePreparedStatement();
		} catch (SQLException ex) {
			Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
		return 1;
	}
}