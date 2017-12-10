package com.model.beans;

public class Journee {
	private int id;
	private String Jour;

	public Journee(int id, String jour) {
		super();
		this.id = id;
		Jour = jour;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJour() {
		return Jour;
	}

	public void setJour(String jour) {
		Jour = jour;
	}

}
