
package com.model.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Equipments {

	private List<Equipment> listEquipments;
	private List<Equipmentsalle> listEquipmentsSalle;

	public Equipments() {
		listEquipments = new ArrayList<Equipment>();
	}

	/**
	 * Get Equipmnet
	 * 
	 * @param id
	 * @return Equipment
	 */

	public Equipment initEquipment(int id) {
		Equipment J = null;
		try {
			Sql.prepareStatement("select * from equipment where id =? ");
			Sql.getPreparedStatement().setInt(1, id);
			ResultSet res = Sql.executPreparedStatement();
			if (res.next()) {
				J = new Equipment(res.getInt("id"), res.getString("nom"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return J;
	}

	public Equipments initEquipments() {
		Equipment j = null;
		ResultSet res = null;
		try {
			Conn.prepareStatement("select * from equipment");
			res = Conn.executPreparedStatement();
			while (res.next()) {
				j = new Equipment(res.getInt("id"), res.getString("nom"));
				listEquipments.add(j);
			}
		} catch (SQLException e) {
			return null;
		}

		return this;
	}
//result.getInt("number")
	public Equipments initEquipments(int id ) {
		Equipment j = null;
		ResultSet res = null;
		try {
			Conn.prepareStatement("select * from Equipment right join (select * from equipmentsalle where id_salle =? ) as table1 on (equipment.id=table1.id_equipment)");
			Conn.getPreparedStatement().setInt(1, id);
			res = Conn.executPreparedStatement();
			while (res.next()) {
				j = new Equipment(res.getInt("id"), res.getString("nom"));
				listEquipments.add(j);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this;
	}
	public int insertEquipment(Equipment e) {
		try {
			Conn.getConnection();
			Conn.prepareStatement("select * from equipment where nom = ?");
			Conn.getPreparedStatement().setString(1, e.getNom());
			ResultSet result = Conn.executPreparedStatement();
			if (!result.next()) {
				Conn.prepareStatement("insert into equipment (nom) values(?)");
				Conn.getPreparedStatement().setString(1, e.getNom());
				Conn.executUpdatePreparedStatement();
			} else {
				return -1;
			}
		} catch (SQLException ex) {
			Logger.getLogger(Equipments.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
		return 1;
	}

	public int updateEquipment(Equipment c) {
		try {
			Conn.prepareStatement("update equipment set id = ? , nom = ? where id = ?");
			Conn.getPreparedStatement().setInt(1, c.getId());
			Conn.getPreparedStatement().setString(2, c.getNom());
			Conn.getPreparedStatement().setInt(3, c.getId());
			Conn.executUpdatePreparedStatement();
		} catch (SQLException ex) {
			Logger.getLogger(Equipments.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
		return 1;
	}

	public int deleteEquipment(int id) {
		try {
			Conn.prepareStatement("delete from equipment where id = ?");
			Conn.getPreparedStatement().setInt(1, id);
			Conn.executUpdatePreparedStatement();
		} catch (SQLException ex) {
			Logger.getLogger(Equipment.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
		return 1;
	}

	public List<Equipment> SearchEquipments(String word) {
		Equipment j = null;
		ResultSet res = null;
		try {
			Conn.prepareStatement("select * from equipment where nom like ? ");
			Conn.getPreparedStatement().setString(1, "%"+word+"%");
			res = Conn.executPreparedStatement();
			while (res.next()) {
				j = new Equipment(res.getInt("id"), res.getString("nom"));
				listEquipments.add(j);
			}
		} catch (SQLException e) {
			return null;
		}

		return listEquipments;
	}


	public List<Equipment> getListEquipments() {
		return listEquipments;
	}

	public void setListEquipments(List<Equipment> listEquipments) {
		this.listEquipments = listEquipments;
	}

	public List<Equipmentsalle> getListEquipmentsSalle() {
		return listEquipmentsSalle;
	}

	public void setListEquipmentsSalle(List<Equipmentsalle> listEquipmentsSalle) {
		this.listEquipmentsSalle = listEquipmentsSalle;
	}

}
