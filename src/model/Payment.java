package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	//A common method to connect to the DB
		private Connection connect() 
		 { 
			Connection con = null; 
			try
		 { 
			 Class.forName("com.mysql.jdbc.Driver"); 
		 
		 //Provide the correct details: DBServer/DBName, username, password 
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", ""); 
		 } 
		 catch (Exception e) 
		 {e.printStackTrace();} 
		 return con; 
		 }
		
		
		public String insertPayment(String ctype, Integer cardno, String expiredate, Integer cvv) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for inserting."; } 
		 // create a prepared statement
		 String query = " insert into payment (`paymentID`,`paymentCtype`,`paymentCno`,`paymentExpire`,`paymentCvv`)"
		 + " values (?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, ctype); 
		 preparedStmt.setInt(3, cardno); 
		 preparedStmt.setString(4, expiredate); 
		 preparedStmt.setInt(5, cvv); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Inserted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while inserting the payment."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }
		
		
		public String readPayment() 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for reading."; } 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>CARD TYPE</th><th>CARD NUMBER</th>" +
		 "<th>EXPIRE DATE</th>" + 
		 "<th>CVV</th></tr>"; 
		 
		 String query = "select * from payment"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String paymentID = Integer.toString(rs.getInt("paymentID")); 
		 String paymentCtype = rs.getString("paymentCtype"); 
		 String paymentCno = rs.getString("paymentCno"); 
		 String paymentExpire = rs.getString("paymentExpire"); 
		 String paymentCvv = rs.getString("paymentCvv"); 
		 
		 // Add into the html table
		 output += "<tr><td>" + paymentCtype + "</td>"; 
		 output += "<td>" + paymentCno + "</td>"; 
		 output += "<td>" + paymentExpire + "</td>"; 
		 output += "<td>" + paymentCvv + "</td>"; 
		 // buttons
		 output +=  "<input name='paymentID' type='hidden' value='" + paymentID 
		 + "'>" + "</form></td></tr>"; 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while reading the payments."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }
		
		
		public String updatePayment(String ID, String ctype, String paymentCno, String expiredate, String paymentCvv)
		{ 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for updating."; } 
			 // create a prepared statement
			 String query = "UPDATE payment SET paymentCtype=?,paymentCno=?,paymentExpire=?,paymentCvv=? WHERE paymentId=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setString(1, ctype); 
			 preparedStmt.setString(2, paymentCno);  
			 preparedStmt.setString(3, expiredate); 
			 preparedStmt.setString(4, paymentCvv); 
			 preparedStmt.setInt(5, Integer.parseInt(ID)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Updated successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while updating the payment."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 } 
		
		
			public String deleteNotice(String paymentID) 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for deleting."; } 
			 // create a prepared statement
			 String query = "delete from payment where paymentID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(paymentID)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Deleted successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while deleting the payment."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 }
			
				
			public String getPaymentDetails(String paymentID)

			{
			String output = "";
			try
			{
			Connection con = connect();
			if (con == null)
			{
			return "Error while connecting to the database for reading";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>CARD TYPE</th><th>CARD NUMBER</th>" +
					 "<th>EXPIRE DATE</th>" + 
					 "<th>CVV</th></tr>";
			String query = "select * from payment where paymentId='"+paymentID+"'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				
			 String paymentId = Integer.toString(rs.getInt("paymentId")); 
			 String paymentCtype = rs.getString("paymentCtype"); 
			 Integer paymentCno = rs.getInt("paymentCno"); 
			 String paymentExpire = rs.getString("paymentExpire"); 
			 Integer paymentCvv = rs.getInt("paymentCvv"); 
			 
			 // Add into the html table
			 output += "<tr><td>" + paymentCtype + "</td>"; 
			 output += "<td>" + paymentCno + "</td>"; 
			 output += "<td>" + paymentExpire + "</td>"; 
			 output += "<td>" + paymentCvv + "</td>"; 
			 // buttons
			 output +=  "<input name='paymentID' type='hidden' value='" + paymentId 
			 + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";

			}
			catch (Exception e)
			{
			output = "Error while reading the payment details";
			System.err.println(e.getMessage());
			}
			return output;
			}

}
