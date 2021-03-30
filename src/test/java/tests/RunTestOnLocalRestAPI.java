package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;
import io.restassured.http.ContentType;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;


public class RunTestOnLocalRestAPI {

	//@Test
	// Fetching all details of the user
	public void testGet()
	{	
		baseURI = "http://localhost:3000";
		
		when().
			get("/users").
		then().
			statusCode(200).
			log().all();
	}

	//@Test
	// For entering a new user
	public void testPost()
	{	
		JSONObject request = new JSONObject();
		request.put("firstName", "Murari");
		request.put("lastName", "Rustagi");
		request.put("subjectId", 2);
		//request.put("id", 4); // Can be given, otherwise it will automatically generate id
		
		baseURI = "http://localhost:3000";
		
		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/users").
		then().
			statusCode(201).
			log().all();
	}
	
	//@Test
	// For updating all the details of user 4
	public void testPut()    
	{	
		JSONObject request = new JSONObject();
		request.put("firstName", "Thomas");
		request.put("lastName", "Edison");
		request.put("subjectId", 1);
		
		baseURI = "http://localhost:3000";
		
		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			put("/users/4").
		then().
			statusCode(200).
			log().all();
	}
	
	//@Test
	// For partially updating entity 4
	public void testPatch()
	{	
		JSONObject request = new JSONObject();
		request.put("firstName", "Albert");

		baseURI = "http://localhost:3000";
		
		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			patch("/users/4").
		then().
			statusCode(200).
			log().all();
	}
	
	//@Test
	// For deleting user 4
	public void testDelete()
	{	
		baseURI = "http://localhost:3000";
		
		when().
			delete("/users/4").
		then().
			statusCode(200).
			log().all();
	}
}
