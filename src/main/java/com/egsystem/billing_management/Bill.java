package com.egsystem.billing_management;

public class Bill {
	private int biil_id;
	private int user_id;
	private String reading_date;
	private String billing_date;
	private int no_of_units;
	private double total_amount;
	
	
	
	public Bill() {
		
	}

	

	public Bill(int biil_id, int user_id, String reading_date, String billing_date, int no_of_units,
			double total_amount) {
		super();
		this.biil_id = biil_id;
		this.user_id = user_id;
		this.reading_date = reading_date;
		this.billing_date = billing_date;
		this.no_of_units = no_of_units;
		this.total_amount = total_amount;
	}



	public int getBiil_id() {
		return biil_id;
	}

	public void setBiil_id(int biil_id) {
		this.biil_id = biil_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getReading_date() {
		return reading_date;
	}

	public void setReading_date(String reading_date) {
		this.reading_date = reading_date;
	}

	public String getBilling_date() {
		return billing_date;
	}

	public void setBilling_date(String billing_date) {
		this.billing_date = billing_date;
	}

	public int getNo_of_units() {
		return no_of_units;
	}

	public void setNo_of_units(int no_of_units) {
		this.no_of_units = no_of_units;
	}

	
	public double getTotal_amount() {
		return total_amount;
	}



	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}



	@Override
	public String toString() {
		return "Bill [biil_id=" + biil_id + ", user_id=" + user_id + ", reading_date=" + reading_date
				+ ", billing_date=" + billing_date + ", no_of_units=" + no_of_units + ", total_amount=" + total_amount
				+ "]";
	}



	
	
	
	
	
}
