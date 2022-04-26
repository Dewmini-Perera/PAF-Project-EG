package com.egsystem.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.egsystem.billing_management.Bill;
import com.egsystem.power_consumption.Unit;

public class BillTableManagement {
	//connect database
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/EGSystemDB","root","root");
			return con;
		}
		catch(Exception e){
			
		}
		return null;
	}
	
	public ResultSet getBills(){
		try {
			Connection con=getConnection();
			Statement st=con.createStatement();
			String query="select * from bill";
			ResultSet rs=st.executeQuery(query);
			return rs;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public ResultSet getBillsFromDate(String date){
		try {
			Connection con=getConnection();
			Statement st=con.createStatement();
			String query="select * from bill where billing_date='"+date+"';";
			ResultSet rs=st.executeQuery(query);
			return rs;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public ResultSet getBillsFromId(int id){
		try {
			Connection con=getConnection();
			Statement st=con.createStatement();
			String query="select * from bill where user_id="+id+";";
			ResultSet rs=st.executeQuery(query);
			return rs;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	
	public void insetBill(Bill b) {
		try{
			Connection con=getConnection();
			Statement st=con.createStatement();
			String query="insert into bill(billing_date,reading_date,user_id,no_of_units,total_amount) values('"+b.getBilling_date()+"','"+b.getReading_date()+"',"+b.getUser_id()+","+b.getNo_of_units()+","+b.getTotal_amount()+");";
			int r=st.executeUpdate(query);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	
	public int updateBill(String old_date,String new_date) {
		try{
			Connection con=getConnection();
			Statement st=con.createStatement();
			String query="update bill set billing_date='"+new_date+"' where billing_date='"+old_date+"';";
			int r=st.executeUpdate(query);
			System.out.println(r);
			return r;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
		
	}
	
	public int deleteBill(int id) {
		try{
			Connection con=getConnection();
			Statement st=con.createStatement();
			String query="delete from bill where ebill_id="+id+";";
			int r=st.executeUpdate(query);
			System.out.println(r);
			return r;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
		
	}
}
