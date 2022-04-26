package com.egsystem.power_consumption;

import java.util.Objects;

//create unit class
public class Unit {
	private int user_id;
	private int unit_consumed;
	private String date;
	private int usage_time;
	
	public Unit() {
		
	}

	

	public Unit(int user_id, int unit_consumed, String date, int usage_time) {
		super();
		this.user_id = user_id;
		this.unit_consumed = unit_consumed;
		this.date = date;
		this.usage_time = usage_time;
	}



	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getUnit_consumed() {
		return unit_consumed;
	}

	public void setUnit_consumed(int unit_consumed) {
		this.unit_consumed = unit_consumed;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}



	public int getUsage_time() {
		return usage_time;
	}



	public void setUsage_time(int usage_time) {
		this.usage_time = usage_time;
	}



	@Override
	public String toString() {
		return "Unit [user_id=" + user_id + ", unit_consumed=" + unit_consumed + ", date=" + date + ", usage_time="
				+ usage_time + "]";
	}
	
	

	

	
	
	
	
}
//test
