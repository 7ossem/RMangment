package com.model.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Journees {
	private List<Journee> listJournees;
	
	public List<Journee> getListJournees() {
		return listJournees;
	}
	public void setListJournees(List<Journee> listJournees) {
		this.listJournees = listJournees;
	}
	public Journees() {
		listJournees = new ArrayList<Journee>();

	}
	public Journee initJournee(int id) {
		Journee J = null;
		try {
			Conn.prepareStatement("select * from journee where id =? ");
			Conn.getPreparedStatement().setInt(1, id);
			ResultSet res = Conn.executPreparedStatement();
			if (res.next()) {
				J = new Journee(res.getInt("id"), res.getString("jour"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return J;
	}

	public Journees initJournees() {
		Journee j = null;
		ResultSet res = null;
		try {
			Conn.prepareStatement("select * from journee  order by id asc");
			res = Conn.executPreparedStatement();
			while (res.next()) {
				j = new Journee(res.getInt("id"), res.getString("jour"));
				listJournees.add(j);
			}
		} catch (SQLException e) {
			return null;
		}

		return this;
	}
	public int insertJournee(String jour) {
		try {
			Conn.prepareStatement("select * from journee where jour = ?");
			Conn.getPreparedStatement().setString(1, jour);
			ResultSet result = Conn.executPreparedStatement();
			if (!result.next()) {
				Conn.prepareStatement("insert into journee(jour)values(?)");
				Conn.getPreparedStatement().setString(1, jour);
				Conn.executUpdatePreparedStatement();
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
			Conn.prepareStatement("update journee set id = ? , name = ? where id = ?");
			Conn.getPreparedStatement().setInt(1, c.getId());
			Conn.getPreparedStatement().setString(2, c.getJour());
			Conn.getPreparedStatement().setInt(3, c.getId());
			Conn.executUpdatePreparedStatement();
		} catch (SQLException ex) {
			Logger.getLogger(Journee.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
		return 1;
	}

	public int deleteJournee(int id) {
		try {
			Conn.prepareStatement("delete from journee where id = ?");
			Conn.getPreparedStatement().setInt(1, id);
			Conn.executUpdatePreparedStatement();
		} catch (SQLException ex) {
			Logger.getLogger(Journee.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
		return 1;
	}

/*	public Journee initJournees(int id) {
		try {
			Conn.prepareStatement(
					"select * from Equipment left join (select * from contains where classroom_number = ? ) as table1 on (Equipment.id=table1.Equipment_id)");
			Conn.getPreparedStatement().setInt(1, id);
			ResultSet result = Conn.executPreparedStatement();

			while (result.next()) {
				Equipments.add(initClassroomEquipment(result));
			}
			return this;
		} catch (ConnException ex) {
			Logger.getLogger(Equipments.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}

	}*/


}
