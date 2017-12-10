
package com.model.beans;

public class Equipment {
	private int id;
	private String Nom;

	public Equipment(int id, String nom) {
		super();
		this.id = id;
		this.Nom = nom;
	}

	public Equipment(String nom) {
		super();
		Nom = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Equipment() {
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}
}
