package tests;

import static io.restassured.RestAssured.*;

import java.util.Iterator;

import io.restassured.http.ContentType;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTestingExample {

	@DataProvider(name = "dataForPost")
	public Object[][] dataForPost()
	{
		Object[][] data = new Object[2][3];	
		
		data[0][0] = "Albert";
		data[0][1] = "Einstein";
		data[0][2] = 1;
		
		data[1][0] = "Thomas";
		data[1][1] = "Edison";
		data[1][2] = 2;
	
		return data;
	}
	
	//@Test(dataProvider = "dataForPost")
	public void testPost(String firstName, String lastName, int subjectId)
	{	
		JSONObject request = new JSONObject();
		request.put("firstName", firstName);
		request.put("lastName", lastName);
		request.put("subjectId", subjectId);
		
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
	
// Other way of creating data
	@DataProvider(name = "dataForPost1")
	public Object[][] dataForPost1()
	{
		Object[][] data = new Object[][]{
				{"Hello","World",1},
				{"Graham","Bell",2}
			};

		return data;
	}
	
	//@Test(dataProvider = "dataForPost1")
	public void testPost1(String firstName, String lastName, int subjectId)
	{	
		JSONObject request = new JSONObject();
		request.put("firstName", firstName);
		request.put("lastName", lastName);
		request.put("subjectId", subjectId);
		
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
	
	@DataProvider(name = "dataForDelete")
	public Object[][] dataForDelete()
	{
		Object[][] data = new Object[][]{
				{4},{5},{6},{7} };
			
		return data;
	}
	
	@Test(dataProvider = "dataForDelete")
	public void testDelete(int userId)
	{	
		baseURI = "http://localhost:3000";
		
		when().
			delete("/users/"+userId).
		then().
			statusCode(200).
			log().all();
	}
}
