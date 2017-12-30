package com.model.beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Salles {
	PreparedStatement stmt;
	ResultSet result;
	private List<Salle> ListSalles;

	public Salles() {
		ListSalles = new ArrayList<Salle>();
	}

	public List<Salle> getListSalles() {
		return ListSalles;
	}

	public void setListSalles(List<Salle> listSalles) {
		ListSalles = listSalles;
	}

	public Salles initSalles() {
		try {
			stmt = Conn.getConnection().prepareStatement("select * from salle");
			result = stmt.executeQuery();
			Salle salle;
			while (result.next()) {
				salle = new Salle();
				salle = initSalle(result);
				ListSalles.add(salle);
			}
		} catch (SQLException ex) {
			Logger.getLogger(Salle.class.getName()).log(Level.SEVERE, null, ex);
		}
		return this;
	}

	public Salle initSalle(ResultSet result) {
		Salle c = new Salle();
		try {
			c.setNum(result.getInt("num"));
			c.setCapacite(result.getInt("capacite"));
			Equipments equipments = new Equipments();
			c.setEquipments((equipments.initEquipments(result.getInt("num")).getListEquipments()));

		} catch (SQLException ex) {
			Logger.getLogger(Salles.class.getName()).log(Level.SEVERE, null, ex);
		}
		return c;
	}

	/* Get Salles By num */
	public Salle initSalle(int num) {
		Salle J = null;
		try {
			Sql.prepareStatement("select * from salle where num =? ");
			Sql.getPreparedStatement().setInt(1, num);
			ResultSet res = Sql.executPreparedStatement();
			if (res.next()) {
				J = new Salle(res.getInt("num"), res.getInt("capacite"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return J;
	}

	public int insertSalle(Salle p) {
		try {
			Conn.prepareStatement("select * from Salle where num = ? ");
			Conn.getPreparedStatement().setInt(1, p.getNum());
			ResultSet result = Conn.executPreparedStatement();
			if (!result.next()) {
				Conn.prepareStatement("insert into Salle(num , capacite) values(? , ?)");
				Conn.getPreparedStatement().setInt(1, p.getNum());
				Conn.getPreparedStatement().setInt(2, p.getCapacite());
				Conn.executUpdatePreparedStatement();
			} else {
				return -1;
			}
		} catch (SQLException ex) {
			Logger.getLogger(Salles.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
		return 1;
	}

	public int updateSalle(Salle c) {
		try {
			Sql.prepareStatement("update Salle set num = ? , capacite = ?   where num = ?");
			Sql.getPreparedStatement().setInt(1, c.getNum());
			Sql.getPreparedStatement().setInt(2, c.getCapacite());
			Sql.getPreparedStatement().setInt(3, c.getNum());
			Sql.executUpdatePreparedStatement();
		} catch (SQLException ex) {
			Logger.getLogger(Salles.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
		return 1;
	}

	public int deleteSalle(int num) {
		try {
			Equipmentsalles e = new Equipmentsalles();
			if (e.deleteEqonesalle(num) == 1) {
				Conn.prepareStatement("delete from Salle where num = ?");
				Conn.getPreparedStatement().setInt(1, num);
				Conn.executUpdatePreparedStatement();
			} else {
				return 0;
			}
		} catch (SQLException ex) {
			Logger.getLogger(Salles.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
		return 1;
	}

	/* search by numero */

	public Salles SearchSalles(int num) {
		ResultSet res = null;
		try {
			Conn.prepareStatement("select * from salle where num = ? ");
			Conn.getPreparedStatement().setInt(1, num);
			res = Conn.executPreparedStatement();
			Salle salle;
			if (res.next()) {
				salle = new Salle();
				salle = initSalle(res);
				ListSalles.add(salle);
			}
		} catch (SQLException e) {
			return null;
		}
		return this;
	}

	public Salles SearchAdvanced(int capcite, int id_jour, int id_Peroie, List<Equipmentsalle> equipments) {

		String query = "";
		if (equipments !=null && equipments.size() > 0) {
			 query += "and ";
			boolean b = false;
			for (Equipmentsalle eq : equipments) {

				if (!b) {
					query += " s.num in (select id_salle from equipmentsalle where id_equipment =" + eq.getIdEquipment()
							+ " and numero  >=" + eq.getNumEquipment() + " ) ";
					b = true;
				} else {
					query += "and" + "and s.num in (select id_salle from equipmentsalle where id_equipment ="
							+ eq.getIdEquipment() + " and numero  >=" + eq.getNumEquipment() + " )";
				}
			}

		}
		System.out.println("Query : " + query);
		try {
			stmt = Conn.getConnection().prepareStatement(
					"Select distinct num , capacite from salle as s inner join equipmentsalle as e on s.num = e.id_salle "
							+ "inner join equipment as eq  on e.id_equipment = eq.id" + " where num not in "
							+ "( select sallefg from reservation where periodefk="+id_Peroie+" And jourfk = "+id_jour+" ) " + "and "
							+ "capacite >="+capcite+" " + " " + query);
			System.out.println("+"+stmt);
			result = stmt.executeQuery();
			Salle salle;
			while (result.next()) {

				salle = new Salle();
				salle = initSallesReservation(result);
				ListSalles.add(salle);
			}
		} catch (SQLException ex) {
			Logger.getLogger(Salles.class.getName()).log(Level.SEVERE, null, ex);
		}
		return this;
	}

	public Salle initSallesReservation(ResultSet result) {
		Salle c = new Salle();
		try {
			// init salle 
			c.setNum(result.getInt("num"));
			c.setCapacite(result.getInt("capacite"));
	      	//init equipment fo salle
			Equipments equipments = new Equipments();
			c.setEquipments((equipments.initEquipments(result.getInt("num")).getListEquipments()));
		    // init Reservation of salle
			Reservations reservations = new Reservations();
			c.setListReservation((reservations.initSalleReservation(result.getInt("num")).getListReservation()));
		} catch (SQLException ex) {
			Logger.getLogger(Salles.class.getName()).log(Level.SEVERE, null, ex);
		}
		return c;
	}

	/* Verifier l'accupation */
	public int verifierAccupation(int num) {
		ResultSet res = null;
		try {
			Conn.prepareStatement(
					"SELECT salle.num, reservation.id FROM  public.salle inner join  public.reservation on reservation.sallefg = salle.num where salle.num = ?");
			Conn.getPreparedStatement().setInt(1, num);
			res = Conn.executPreparedStatement();

			if (res.next()) {
				// la salle est reserver
				return 1;
			}
		} catch (SQLException e) {
			// internal Error
			return 0;
		}
		// la salle n exist pas
		return -1;
	}
}
