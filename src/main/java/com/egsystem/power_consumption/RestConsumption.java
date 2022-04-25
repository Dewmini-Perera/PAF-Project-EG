package com.egsystem.power_consumption;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.egsystem.database.UnitTableManagement;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("consumption")
public class RestConsumption {

		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Unit> getData() {
			
			List<Unit> list=new ArrayList<>();
			
			UnitTableManagement db=new UnitTableManagement();
			
			ResultSet rs=db.getUnits();
			try {
				while(rs.next()) {
					Unit c=new Unit();
//					System.out.println(rs.getInt("user_id"));
//					System.out.println(rs.getInt("unit_consiumed"));
//					System.out.println(rs.getString("date"));
					
					c.setDate(rs.getString("date"));
					c.setUser_id(rs.getInt("user_id"));
					c.setUnit_consumed(rs.getInt("unit_consiumed"));
					c.setUsage_time(rs.getInt("usage_time"));
					
					list.add(c);
				}
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
			
			return list;
		}
		
		
		@GET
		@Path("date/{date}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<Unit> getDataFromDate(@PathParam("date") String date) {
			
			List<Unit> list=new ArrayList<>();
			
			UnitTableManagement db=new UnitTableManagement();
			
			ResultSet rs=db.getUnitsFromDate(date);
			try {
				while(rs.next()) {
					Unit c=new Unit();
					System.out.println(rs.getInt("user_id"));
					System.out.println(rs.getInt("unit_consiumed"));
					System.out.println(rs.getString("date"));
					
					c.setDate(rs.getString("date"));
					c.setUser_id(rs.getInt("user_id"));
					c.setUnit_consumed(rs.getInt("unit_consiumed"));
					c.setUsage_time(rs.getInt("usage_time"));
					
					list.add(c);
				}
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
			
			return list;
		}
		
		
		
		@GET
		@Path("id/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<Unit> getDataFromId(@PathParam("id") int id) {
			
			List<Unit> list=new ArrayList<>();
			
			UnitTableManagement db=new UnitTableManagement();
			
			ResultSet rs=db.getUnitsFromId(id);
			try {
				while(rs.next()) {
					Unit c=new Unit();
					System.out.println(rs.getInt("user_id"));
					System.out.println(rs.getInt("unit_consiumed"));
					System.out.println(rs.getString("date"));
					
					c.setDate(rs.getString("date"));
					c.setUser_id(rs.getInt("user_id"));
					c.setUnit_consumed(rs.getInt("unit_consiumed"));
					c.setUsage_time(rs.getInt("usage_time"));
					
					list.add(c);
				}
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
			
			return list;
		}
		
		
		@POST
		@Path("add")
		public String addUnit(Unit c) {
			System.out.println(c.getDate());
			UnitTableManagement db=new UnitTableManagement();
			db.insetUnit(c);
			
			return "Data Added Successfully!";
		}
		
		
		@PUT
		@Path("update")
		public String updateUnit(Unit c) {
			System.out.println(c.getDate());
			UnitTableManagement db=new UnitTableManagement();
			int a=db.updateUnit(c);
			if(a>0) {
				return "Data Updated Successfully!";
			}
			return "Can't Update";
		}
		
		@DELETE
		@Path("delete/{id}")
		public String deleteUnit(@PathParam("id") int id) {
			UnitTableManagement db=new UnitTableManagement();
			int a=db.deleteUnit(id);
			if(a>0) {
				return "Data Deleted Successfully!";
			}
			return "Can't Delete";
		}
		
		
}
