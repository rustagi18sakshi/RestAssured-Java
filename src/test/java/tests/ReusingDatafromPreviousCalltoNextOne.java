package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
/*
Website to refer : https://semaphoreci.com/community/tutorials/testing-rest-endpoints-using-rest-assured
*/
public class ReusingDatafromPreviousCalltoNextOne {

	//@Test
	public void testGetUsingQueryParam()
	{
		JSONObject car = new JSONObject();
		car.put("plateNumber", "xyx1111");
		car.put("brand", "audi");
		car.put("colour", "red");
		
		System.out.println(car.toJSONString());
		
		baseURI = "https://reqres.in/api";  // Any URL
		
		// POST request
		/* Response obtained is like : 		{  
   												"empty":false,
   												"position":26
											}
		*/
		int positionTakenInGarage = given().
			header("Content-Type","application/json").
			contentType(ContentType.JSON).  //Request should be sent in JSON format
			accept(ContentType.JSON).       //Response should be received in JSON format
			body(car.toJSONString()).
		when().
			post("/garage/slots").
		then().
			extract().path("position");
		
		// Delete request where we are assigning value of 'positionTakenInGarage' to parameter 'slotID'
		given().
			pathParam("slotID",positionTakenInGarage).
		when().
			delete("/garage/slots/{slotID}").
		then().
			statusCode(200);
	}
}
