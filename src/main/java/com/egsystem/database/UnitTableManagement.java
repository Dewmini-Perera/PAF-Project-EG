package com.egsystem.database;

import java.sql.Statement;

import com.egsystem.power_consumption.Unit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class UnitTableManagement {

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
	
	public ResultSet getUnits(){
		try {
			Connection con=getConnection();
			Statement st=con.createStatement();
			String query="select * from unit";
			ResultSet rs=st.executeQuery(query);
			return rs;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public ResultSet getUnitsFromDate(String date){
		try {
			Connection con=getConnection();
			Statement st=con.createStatement();
			String query="select * from unit where date='"+date+"';";
			ResultSet rs=st.executeQuery(query);
			return rs;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public ResultSet getUnitsFromId(int id){
		try {
			Connection con=getConnection();
			Statement st=con.createStatement();
			String query="select * from unit where user_id="+id+";";
			ResultSet rs=st.executeQuery(query);
			return rs;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	
	public void insetUnit(Unit c) {
		try{
			Connection con=getConnection();
			Statement st=con.createStatement();
			String query="insert into unit(user_id,unit_consiumed,date,usage_time) values("+c.getUser_id()+","+c.getUnit_consumed()+",'"+c.getDate()+"',"+c.getUsage_time()+");";
			int r=st.executeUpdate(query);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	
	public int updateUnit(Unit c) {
		try{
			Connection con=getConnection();
			Statement st=con.createStatement();
			String query="update unit set date='"+c.getDate()+"', user_id="+c.getUser_id()+", unit_consiumed ="+c.getUnit_consumed()+",usage_time="+c.getUsage_time()+" where user_id="+c.getUser_id()+";";
			int r=st.executeUpdate(query);
			System.out.println(r);
			return r;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
		
	}
	
	public int deleteUnit(int id) {
		try{
			Connection con=getConnection();
			Statement st=con.createStatement();
			String query="delete from unit where user_id="+id+";";
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
