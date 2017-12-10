package com.model.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Journees {

	public Journee initJournee(int id) {
		Journee J = null;
		try {
			Sql.prepareStatement("select * from journee where id =? ");
			Sql.getPreparedStatement().setInt(1, id);
			ResultSet res = Sql.executPreparedStatement();
			if (res.next()) {
				J = new Journee(res.getInt("id"), res.getString("jour"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return J;
	}

	public int insertJournee(String jour) {
		try {
			Sql.prepareStatement("select * from journee where jour = ?");
			Sql.getPreparedStatement().setString(1, jour);
			ResultSet result = Sql.executPreparedStatement();
			if (!result.next()) {
				Sql.prepareStatement("insert into journee(jour)values(?)");
				Sql.getPreparedStatement().setString(1, jour);
				Sql.executUpdatePreparedStatement();
			} else {
				return -1;
			}
		} catch (SQLException ex) {
			Logger.getLogger(Journees.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
		return 1;
	}

	public int updateJournee(Journee c) {
		try {
			Sql.prepareStatement("update journee set id = ? , name = ? where id = ?");
			Sql.getPreparedStatement().setInt(1, c.getId());
			Sql.getPreparedStatement().setString(2, c.getJour());
			Sql.getPreparedStatement().setInt(3, c.getId());
			Sql.executUpdatePreparedStatement();
		} catch (SQLException ex) {
			Logger.getLogger(Journee.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
		return 1;
	}

	public int deleteJournee(int id) {
		try {
			Sql.prepareStatement("delete from journee where id = ?");
			Sql.getPreparedStatement().setInt(1, id);
			Sql.executUpdatePreparedStatement();
		} catch (SQLException ex) {
			Logger.getLogger(Journee.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
		return 1;
	}

/*	public Journee initJournees(int id) {
		try {
			Sql.prepareStatement(
					"select * from Equipment left join (select * from contains where classroom_number = ? ) as table1 on (Equipment.id=table1.Equipment_id)");
			Sql.getPreparedStatement().setInt(1, id);
			ResultSet result = Sql.executPreparedStatement();

			while (result.next()) {
				Equipments.add(initClassroomEquipment(result));
			}
			return this;
		} catch (SQLException ex) {
			Logger.getLogger(Equipments.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}

	}*/


}
