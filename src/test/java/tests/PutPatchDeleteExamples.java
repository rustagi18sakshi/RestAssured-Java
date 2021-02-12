package tests;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class PutPatchDeleteExamples {

	@Test
	//Put request is used for updating an existing resource
	public void testPut()
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
			put("/users/2").
		then().
			statusCode(200).
			log().all();
	}
	
	@Test
	//Patch request is used for partially updating an existing resource.
	public void testPatch()
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
			patch("/users/2").
		then().
			statusCode(200).
			log().all();
	}
	
	@Test
	//Delete request is used for deleting an existing resource
	public void testDelete()
	{	
		
		baseURI = "https://reqres.in/api";
		
		when().
			delete("/users/2").
		then().
			statusCode(204).
			log().all();
	}
}
