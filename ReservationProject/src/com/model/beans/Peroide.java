
package com.model.beans;

public class Peroide {
	private int id;
	private String startTime;
	private String endTime;

	/**
	 * 
	 * @param id
	 * @param startTime
	 * @param endTime
	 */
	public Peroide(int id, String startTime, String endTime) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
