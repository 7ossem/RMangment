package com.model.beans;

public class Reservations {
	private int id;
	private int id_salle;
	private int id_Periode;
	private int id_Journee;

	public Reservations() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_salle() {
		return id_salle;
	}

	public void setId_salle(int id_salle) {
		this.id_salle = id_salle;
	}

	public int getId_Periode() {
		return id_Periode;
	}

	public void setId_Periode(int id_Periode) {
		this.id_Periode = id_Periode;
	}

	public int getId_Journee() {
		return id_Journee;
	}

	public void setId_Journee(int id_Journee) {
		this.id_Journee = id_Journee;
	}

}
