package com.model.beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Salle {
	private int Num;
	private int  Capacite;
	/* private String Type; */
	private PreparedStatement statement;
	private ResultSet result;
	private List<Equipment> Equipments;
    
	
	public Salle(int num, int capacite) {
		super();
		Num = num;
		Capacite = capacite;
	}
	public Salle() {
		Equipments = new ArrayList<Equipment>();
	}
	public PreparedStatement getStatement() {
		return statement;
	}

	public void setStatement(PreparedStatement statement) {
		this.statement = statement;
	}

	public ResultSet getResult() {
		return result;
	}

	public void setResult(ResultSet result) {
		this.result = result;
	}

	public List<Equipment> getEquipments() {
		return Equipments;
	}

	public void setEquipments(List<Equipment> equipments) {
		this.Equipments = equipments;
	}
	

	public int getNum() {
		return Num;
	}

	public void setNum(int num) {
		Num = num;
	}

	public int getCapacite() {
		return Capacite;
	}

	public void setCapacite(int capacite) {
		Capacite = capacite;
	}

	
	/*
	 * public String getType() { return Type; }
	 */

	/*
	 * public void setType(String type) { Type = type; }
	 */
}
