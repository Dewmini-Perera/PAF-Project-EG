package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Users {
	// A common method to connect to the DB
			private Connection connect() {
				Connection con = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");

					// Provide the correct details: DBServer/DBName, username, password
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usermanage", "root", "");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return con;
			}

			public String insertItem(String code, String name, String nic, String phone, String email, String address,
					String password) {
				String output = "";
				try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for inserting.";
					}
					// create a prepared statement
					String query = " insert into user(`PID`,`Pcode`,`PName`,`PNIC`,`PhoneNo`,`Email`,`Address`,`Password`)"
							+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, code);
					preparedStmt.setString(3, name);
					preparedStmt.setString(4, nic);
					preparedStmt.setString(5, phone);
					preparedStmt.setString(6, email);
					preparedStmt.setString(7, address);
					preparedStmt.setString(8, password);
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Inserted successfully";
				} catch (Exception e) {
					output = "Error while inserting the item.";
					System.err.println(e.getMessage());
				}
				return output;
			}

			public String readItems() {
				String output = "";
				try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for reading.";
					}
					// Prepare the html table to be displayed
					output = "<table border=\"2\"><tr><th>User Code</th><th>Name</th><th>NIC</th><th>PhoneNo</th><th>Email</th>"
							+ "<th>Address</th><th>Password</th><th>Update</th><th>Remove</th></tr>";
					String query = "select * from user";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					// iterate through the rows in the result set
					while (rs.next()) {
						String PID = Integer.toString(rs.getInt("PID"));
						String Pcode = rs.getString("Pcode");
						String PName = rs.getString("PName");
						String PNIC = rs.getString("PNIC");
						String PhoneNo = rs.getString("PhoneNo");
						String Email = rs.getString("Email");
						String Address = rs.getString("Address");
						String Password = rs.getString("Password");
						// Add into the html table
						output += "<tr><td>" + Pcode + "</td>";
						output += "<td>" + PName + "</td>";
						output += "<td>" + PNIC + "</td>";
						output += "<td>" + PhoneNo + "</td>";
						output += "<td>" + Email + "</td>";
						output += "<td>" + Address + "</td>";
						output += "<td>" + Password + "</td>";
						// buttons
						output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
								+ "<td><form method=\"post\" action=\"users.jsp\">"
								+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
								+ "<input name=\"PID\" type=\"hidden\" value=\"" + PID + "\">" + "</form></td></tr>";
					}
					con.close();
					// Complete the html table
					output += "</table>";
				} catch (Exception e) {
					output = "Error while reading the items.";
					System.err.println(e.getMessage());
				}
				return output;
			}

			public String updateItem(String id,String code, String name, String nic, String phone, String email, String address,
					String password) {
				String output = "";
				try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for updating.";
					}
					// create a prepared statement
					String query = "UPDATE user SET Pcode=?,PName=?,PNIC=?,PhoneNo=?,Email=?,Address=?,Password=?WHERE PID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setString(1, code);
					preparedStmt.setString(2, name);
					preparedStmt.setString(3, nic);
					preparedStmt.setString(4, phone);
					preparedStmt.setString(5, email);
					preparedStmt.setString(6, address);
					preparedStmt.setString(7, password);
					preparedStmt.setInt(8, Integer.parseInt(id));
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Updated successfully";
				} catch (Exception e) {
					output = "Error while updating the item.";
					System.err.println(e.getMessage());
				}
				return output;
			}

			public String deleteItem(String itemID) {
				String output = "";
				try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for deleting.";
					}
					// create a prepared statement
					String query = "delete from user where PID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(itemID));
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Deleted successfully";
				} catch (Exception e) {
					output = "Error while deleting the item.";
					System.err.println(e.getMessage());
				}
				return output;
			}
		}

