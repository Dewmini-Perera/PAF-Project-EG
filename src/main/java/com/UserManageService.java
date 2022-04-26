package com;

import model.Users;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Users")
public class UserManageService {

	Users itemObj = new Users();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return itemObj.readItems();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(
			@FormParam("Pcode") String Pcode,
			@FormParam("PName") String PName,
			@FormParam("PNIC") String PNIC, 
			@FormParam("PhoneNo") String PhoneNo,
	        @FormParam("Email") String Email,
            @FormParam("Address") String Address,
            @FormParam("Password") String Password)
	{
		String output = itemObj.insertItem(Pcode, PName, PNIC, PhoneNo, Email, Address, Password);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData) {
		// Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		// Read the values from the JSON object
		String PID = itemObject.get("PID").getAsString();
		String Pcode = itemObject.get("Pcode").getAsString();
		String PName = itemObject.get("PName").getAsString();
		String PNIC = itemObject.get("PNIC").getAsString();
		String PhoneNo = itemObject.get("PhoneNo").getAsString();
		String Email = itemObject.get("Email").getAsString();
		String Address = itemObject.get("Address").getAsString();
		String Password = itemObject.get("Password").getAsString();
		String output = itemObj.updateItem(PID, Pcode, PName, PNIC, PhoneNo, Email, Address, Password);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String itemID = doc.select("PID").text();
		String output = itemObj.deleteItem(itemID);
		return output;
	}

}
