package com.model.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Equipmentsalles {
	
	public Equipmentsalle initEquipmentsalle(int idsalle,int idequipment) {
		Equipmentsalle J = null;
		try {
			Sql.prepareStatement("select * from equipmentsalle where id_equipment =? and id_salle =?");
			Sql.getPreparedStatement().setInt(1, idequipment);
			Sql.getPreparedStatement().setInt(1, idsalle);
			ResultSet res = Sql.executPreparedStatement();
			if (res.next()) {
				J = new Equipmentsalle(res.getInt("id_salle"),res.getInt("id_equipment"), res.getInt("numero"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return J;
	}

	public int insertEquipementsalle(Equipmentsalle e) {
		try {
			Sql.prepareStatement("select * from equipmentsalle where id_salle= ? and id_equipment=? ");
			Sql.getPreparedStatement().setInt(1,e.getIdSalle() );
			Sql.getPreparedStatement().setInt(2, e.getIdEquipment());
			ResultSet result = Sql.executPreparedStatement();
			if (!result.next()) {
				Sql.prepareStatement("insert into equipmentsalle values(?,?,?)");
				Sql.getPreparedStatement().setInt(1,e.getIdSalle() );
				Sql.getPreparedStatement().setInt(2, e.getIdEquipment());
				Sql.getPreparedStatement().setInt(2, e.getNumEquipment());
				Sql.executUpdatePreparedStatement();
			} else {
				return -1;
			}
		} catch (SQLException ex) {
			Logger.getLogger(Equipmentsalle.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
		return 1;
	}

	public int updateEquipmentsalle(Equipmentsalle c) {
		try {
			Sql.prepareStatement("update Equipmentsalle set id_salle = ? , id_equipment = ?  , numero =? where id_salle = ? and id_equipment= ?");
			Sql.getPreparedStatement().setInt(1, c.getIdSalle());
			Sql.getPreparedStatement().setInt(2, c.getIdEquipment());
			Sql.getPreparedStatement().setInt(3,c.getNumEquipment());
			Sql.getPreparedStatement().setInt(4, c.getIdSalle());
			Sql.getPreparedStatement().setInt(5, c.getIdEquipment());
			Sql.executUpdatePreparedStatement();
		} catch (SQLException ex) {
			Logger.getLogger(Equipmentsalle.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
		return 1;
	}

	public int deleteEquipmentsalle(int id_salle , int id_equipment) {
		try {
			Sql.prepareStatement("delete from equipmentsalle where id_salle = ? and id_equipment = ?");
			Sql.getPreparedStatement().setInt(1, id_salle);
			Sql.getPreparedStatement().setInt(1, id_equipment);
			Sql.executUpdatePreparedStatement();
		} catch (SQLException ex) {
			Logger.getLogger(Equipmentsalle.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
		return 1;
	}
	/* Delete All Equipment for one salle*/
	public int deleteEqonesalle(int id_salle) {
		try {
			Conn.prepareStatement("delete from equipmentsalle where id_salle = ?");
			Conn.getPreparedStatement().setInt(1, id_salle);
			Conn.executUpdatePreparedStatement();
		} catch (SQLException ex) {
			Logger.getLogger(Equipmentsalle.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
		return 1;
	}
	/* Get Numbre of component of one room*/
	public int getNumbreEquipment(int idsalle,int idequipment) {
		ResultSet res = null;
		try {
			System.out.println(idsalle +"-"+idequipment);
			Conn.prepareStatement("select * from equipmentsalle where id_equipment =? and id_salle =?");
			Conn.getPreparedStatement().setInt(1, idequipment);
			Conn.getPreparedStatement().setInt(2, idsalle);
			 res = Conn.executPreparedStatement();
			if (res.next()) {
              return res.getInt("numero");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;
		}
		return -1;
	}


	
}
