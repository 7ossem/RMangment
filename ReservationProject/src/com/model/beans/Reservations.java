package com.model.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reservations {

	private List<Reservation> listReservation;

	public Reservations() {
		listReservation = new ArrayList<Reservation>();
	}
	/*
	 * select * from salle as s where s.num not in ( select sallefg from
	 * reservation where periodefk=1 And jourfk = 1 ) and s.capacite >= 40 and
	 * s.num not in (select id_salle from equipmentsalle where id_equipment =1
	 * and numero = 1)
	 * 
	 */

	public Reservations SearchAdvanced(int capcite, int id_jour, int id_Peroie, Equipment equipments) {

		try {

		} catch (Exception e) {

			e.printStackTrace();

			return null;
		}
		return this;
	}

	public Reservations initSalleReservation(int num) {
		Reservation j = null;
		ResultSet res = null;
		try {
			Conn.prepareStatement(" select r.id , r.sallefg , r.jourfk , r.periodefk  from salle as s inner join "
					+ " ( select * from reservation where sallefg =? ) as r on ( r.sallefg = s.num) ");
			Conn.getPreparedStatement().setInt(1, num);
			res = Conn.executPreparedStatement();
			while (res.next()) {
				j = new Reservation();
			   j.setId(res.getInt("id"));
			   j.setId_salle((res.getInt("sallefg")));
			   j.setId_Journee((res.getInt("jourfk")));
			   j.setId_Periode((res.getInt("periodefk"))); 
				listReservation.add(j);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this;	
		}
	
	public int  ReserverSalle(int ids ,int idp , int jid) {
		try {
			Conn.prepareStatement("select * from reservation where sallefg=? and periodefk=? and jourfk= ? ");
			Conn.getPreparedStatement().setInt(1, ids);
			Conn.getPreparedStatement().setInt(2,idp );
			Conn.getPreparedStatement().setInt(3, jid);
			ResultSet result = Conn.executPreparedStatement();
			if (!result.next()) {
				Conn.prepareStatement("INSERT INTO public.reservation"
						+ "(sallefg, jourfk, periodefk)   VALUES ( ?, ?, ?)");
				Conn.getPreparedStatement().setInt(1, ids);
				Conn.getPreparedStatement().setInt(2, jid);
				Conn.getPreparedStatement().setInt(3,idp );
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
	public int AnnulerReservation(int ids ,int idp , int jid) {
		try {
			
				Conn.prepareStatement("DELETE FROM reservation WHERE sallefg = ? and periodefk = ? and jourfk = ? ");
				Conn.getPreparedStatement().setInt(1, ids);
				Conn.getPreparedStatement().setInt(2, idp);
				Conn.getPreparedStatement().setInt(3, jid);
				System.out.println(Conn.getPreparedStatement());

				Conn.executUpdatePreparedStatement();

		} catch (SQLException ex) {
			Logger.getLogger(Salles.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
		return 1;
	}

	public List<Reservation> getListReservation() {
		return listReservation;
	}

	public void setListReservation(List<Reservation> listReservation) {
		this.listReservation = listReservation;
	}

}
