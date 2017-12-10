package com.model.beans;

public class Equipmentsalle {
	private int idSalle, idEquipment;
	private int NumEquipment;

	public Equipmentsalle(int idSalle, int idEquipment, int numEquipment) {
		super();
		this.idSalle = idSalle;
		this.idEquipment = idEquipment;
		NumEquipment = numEquipment;
	}

	public int getIdSalle() {
		return idSalle;
	}

	public void setIdSalle(int idSalle) {
		this.idSalle = idSalle;
	}

	public int getIdEquipment() {
		return idEquipment;
	}

	public void setIdEquipment(int idEquipment) {
		this.idEquipment = idEquipment;
	}

	public int getNumEquipment() {
		return NumEquipment;
	}

	public void setNumEquipment(int numEquipment) {
		NumEquipment = numEquipment;
	}

}
