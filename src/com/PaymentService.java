package com;
import model.Payment; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser; 
@Path("/Payments") 

public class PaymentService {
	Payment paymentObj = new Payment(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readPayment() 
	 { 
		return paymentObj.readPayment();
	 }
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String insertPayment(
	 @FormParam("paymentCtype") String paymentCtype, 
	 @FormParam("paymentCno") Integer paymentCno, 
	 @FormParam("paymentExpire") String paymentExpire, 
	 @FormParam("paymentCvv") Integer paymentCvv) 
	{ 
		String output = paymentObj.insertPayment(paymentCtype, paymentCno, paymentExpire, paymentCvv); 
		return output; 
	}
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updatePayment(String paymentData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String paymentID = paymentObject.get("paymentID").getAsString(); 
	 String paymentCtype = paymentObject.get("paymentCtype").getAsString(); 
	 String paymentCno = paymentObject.get("paymentCno").getAsString(); 
	 String paymentExpire = paymentObject.get("paymentExpire").getAsString(); 
	 String paymentCvv = paymentObject.get("paymentCvv").getAsString(); 
	 String output = paymentObj.updatePayment(paymentID, paymentCtype, paymentCno, paymentExpire, paymentCvv); 
	return output; 
	}

	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deletePayment(String paymentData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String paymentID = doc.select("paymentID").text(); 
	 String output = paymentObj.deleteNotice(paymentID); 
	return output; 
	}
	
	
	@GET
	@Path("/getPaymentbyID/{paymentId}")//view a specific user
	@Produces(MediaType.TEXT_HTML)
	public String PaymentDetails(@PathParam("paymentId") String paymentId) {

	return paymentObj.getPaymentDetails(paymentId);
	}

}
