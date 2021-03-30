package tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
/*
 Website for fake data: https://reqres.in/ 
*/
public class BasicExample {

	//GET example
	@Test
	public void test_1()
	{
		Response response = RestAssured.get("https://reqres.in/api/users?page=2");
		
		System.out.println("Status code of the response is : "+response.getStatusCode());
		System.out.println("Response time is : "+response.getTime());
		System.out.println("Response body is : "+response.getBody().asString());
		System.out.println("Response status line is : "+response.getStatusLine());
		System.out.println("Response header content type is : "+response.getHeader("content-type"));
		System.out.println("Response header header name is : "+response.getHeader("headerName"));

		// Get all cookies as simple name-value pairs
		Map<String, String> allCookies = response.getCookies();
		
		for(Map.Entry m :allCookies.entrySet())
		{
			System.out.println(m.getKey() +"  "+m.getValue());
		}
		//Assertions 
		int status = response.getStatusCode();
		Assert.assertEquals(status, 200);
	}
}
