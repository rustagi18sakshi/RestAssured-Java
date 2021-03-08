package tests;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
/*
Website to used for fake data : https://reqres.in/
JSON body path to be found through JSON Path Finder website.
*/
public class GetAndPostExamples {
	
	@Test
	//GET requests are used to retrieve resource information only
	public void testGet()
	{
		baseURI = "https://reqres.in/api";
		
		when().
			get("users?page=2").
		then().
			statusCode(200).
			body("data[4].first_name", equalTo("George")).  //JSON path to be found through JSON Path Finder
			body("data.first_name", hasItems("George","Rachel")).
			log().all();
	}

	@Test
	//POST requests are used to create a new resource into the collection of resources
	public void testPost()
	{	
		JSONObject request = new JSONObject();
		request.put("name", "Sakshi");
		request.put("job", "teacher");
		
		System.out.println(request.toJSONString());
		
		baseURI = "https://reqres.in/api";
		
		given().
			header("Content-Type","application/json").
			contentType(ContentType.JSON).  //Request should be sent in JSON format
			accept(ContentType.JSON).       //Response should be received in JSON format
			body(request.toJSONString()).
		when().
			post("/users").
		then().
			statusCode(201).
			log().all();
	}
}
