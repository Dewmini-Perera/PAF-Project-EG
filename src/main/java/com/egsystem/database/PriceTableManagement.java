package com.egsystem.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PriceTableManagement {

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
	
	public ResultSet getPrice(){
		try {
			Connection con=getConnection();
			Statement st=con.createStatement();
			String query="select * from unit_price";
			ResultSet rs=st.executeQuery(query);
			return rs;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
