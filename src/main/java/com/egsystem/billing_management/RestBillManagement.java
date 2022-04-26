package com.egsystem.billing_management;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.egsystem.database.BillTableManagement;
import com.egsystem.database.PriceTableManagement;
import com.egsystem.power_consumption.Unit;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("bill")
public class RestBillManagement {
	
	@POST
	@Path("add")
	public String test(String date) {
		//System.out.println("Working");
		try {
			URL url = new URL("http://localhost:8080/EG_System/rest/consumption");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			//Getting the response code
			int responsecode = conn.getResponseCode();
			//System.out.println(responsecode);
			
			if (responsecode != 200) {
			    throw new RuntimeException("HttpResponseCode: " + responsecode);
			} 
			else {
			  
			    String inline = "";
			    Scanner scanner = new Scanner(url.openStream());
			  
			    while (scanner.hasNext()) {
			       inline += scanner.nextLine();
			    }
			    scanner.close();
			    final ObjectMapper objectMapper = new ObjectMapper();
			    Unit[] units = objectMapper.readValue(inline, Unit[].class);
			    
			    List<Unit> unitList = new ArrayList(Arrays.asList(units));
			    
			    //System.out.println(unitList);
			    for(int i=0;i<unitList.size();i++){
			    	Unit u=unitList.get(i);
			    	Bill bill=new Bill();
			    	
			    	bill.setBilling_date(date);
			    	bill.setNo_of_units(u.getUnit_consumed());
			    	bill.setReading_date(u.getDate());
			    	bill.setUser_id(u.getUser_id());
			    	
			    	PriceTableManagement pt=new PriceTableManagement();
			    	
			    	ResultSet rs=pt.getPrice();
			    	double total=0;
			    	int unit=u.getUnit_consumed();
			    	rs.next();
			    	if(unit>30) {
			    		
			    		total+=30*rs.getDouble("price");
			    		unit-=30;
			    		if(unit>30) {
			    			rs.next();
			    			total+=30*rs.getDouble("price");
			    			unit-=30;
			    			
			    			rs.next();
			    			total+=unit*rs.getDouble("price");
			    		}
			    		else {
			    			rs.next();
			    			total+=30*rs.getDouble("price");
			    		}
			    	}
			    	else {
			    		total+=unit*rs.getDouble("price");
			    	}
			    	bill.setTotal_amount(total);
			    	
			    	BillTableManagement bt=new BillTableManagement();
			    	boolean t=true;
			    	rs=bt.getBills();
			    	while(rs.next()) {
			    		if(rs.getInt("user_id")==u.getUser_id() && u.getDate().equals(rs.getString("reading_date")) ) {
			    			t=false;
			    			break;
			    		}
			    	}
			    	if(t) {
			    		bt.insetBill(bill);
			    	}
			    	
			    	
			    }
			    
			    
			    
			}
			
			return "Data added!";
			
			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return "Can't add data!";
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Bill> getBills(){
		List<Bill> bills=new ArrayList<>();
		
		BillTableManagement bt=new BillTableManagement();
		
		ResultSet rs=bt.getBills();
		
		try {
			while(rs.next()) {
				Bill b=new Bill();
				b.setBiil_id(rs.getInt("ebill_id"));
				b.setBilling_date(rs.getString("billing_date"));
				b.setNo_of_units(rs.getInt("no_of_units"));
				b.setReading_date(rs.getString("reading_date"));
				b.setUser_id(rs.getInt("user_id"));
				b.setTotal_amount(rs.getDouble("total_amount"));
				
				bills.add(b);
				
			}
			System.out.println("Done");
			return bills;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return bills;
		
		
	}
	
	@GET
	@Path("date/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Bill> getBillsByDate(@PathParam("date") String date){
		List<Bill> bills=new ArrayList<>();
		
		BillTableManagement bt=new BillTableManagement();
		
		ResultSet rs=bt.getBillsFromDate(date);
		
		try {
			while(rs.next()) {
				Bill b=new Bill();
				b.setBiil_id(rs.getInt("ebill_id"));
				b.setBilling_date(rs.getString("billing_date"));
				b.setNo_of_units(rs.getInt("no_of_units"));
				b.setReading_date(rs.getString("reading_date"));
				b.setUser_id(rs.getInt("user_id"));
				b.setTotal_amount(rs.getDouble("total_amount"));
				
				bills.add(b);
				
			}
			System.out.println("Done");
			return bills;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return bills;
		
		
	}
	
	@GET
	@Path("id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Bill> getBillsById(@PathParam("id") int id){
		List<Bill> bills=new ArrayList<>();
		
		BillTableManagement bt=new BillTableManagement();
		
		ResultSet rs=bt.getBillsFromId(id);
		
		try {
			while(rs.next()) {
				Bill b=new Bill();
				b.setBiil_id(rs.getInt("ebill_id"));
				b.setBilling_date(rs.getString("billing_date"));
				b.setNo_of_units(rs.getInt("no_of_units"));
				b.setReading_date(rs.getString("reading_date"));
				b.setUser_id(rs.getInt("user_id"));
				b.setTotal_amount(rs.getDouble("total_amount"));
				
				bills.add(b);
				
			}
			System.out.println("Done");
			return bills;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return bills;
		
		
	}
	
	@DELETE
	@Path("delete/{id}")
	public String deleteBill(@PathParam("id") int id) {
		BillTableManagement bt=new BillTableManagement();
		int a=bt.deleteBill(id);
		if(a>0) {
			return "deleted!";
		}
		return "can't delete";
	}
	//put request
	@PUT
	@Path("update/date")
	public String updateDate(String date) {
		String dates[]=date.split(System.lineSeparator());
		BillTableManagement bt=new BillTableManagement();
		int a=bt.updateBill(dates[0],dates[1]);
		System.out.println(dates[0]);
		System.out.println(dates[1]);
		if(a>0) {
			return "Updated!";
		}
		return "can't update";
		
	}
	
	
}
