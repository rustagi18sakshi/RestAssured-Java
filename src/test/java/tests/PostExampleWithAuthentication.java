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
public class PostExampleWithAuthentication {
	
	@Test
	// By default, REST Assured waits for the server to challenge before sending the credentials.
	// With Preemptive Authentication in place, REST Assured will send the credentials without waiting for an Unauthorized response.
	public void testPostWithPreemptiveAuthentication()
	{	

		baseURI = "http://localhost:8080";

		String validRequest = "{\n" +
	            "  \"username\": \"some-user\",\n" +
	            "  \"email\": \"some-user@email.com\",\n" +
	            "  \"password\": \"Passw0rd123!\" \n}";

		given().
			auth().
			preemptive().
			basic("username", "password").
			header("Content-Type","application/json").
			contentType(ContentType.JSON).  //Request should be sent in JSON format
			accept(ContentType.JSON).       //Response should be received in JSON format
			body(validRequest).
		when().
			post("/users").
		then().
			statusCode(201).
			log().all();
	}
	
	@Test
	// When using "basic authentication" REST Assured will not supply the credentials unless the server 
	// has explicitly asked for it. 
	public void testPostWithBasicAuthentication()
	{	

		baseURI = "http://localhost:8080";

		String validRequest = "{\n" +
	            "  \"username\": \"some-user\",\n" +
	            "  \"email\": \"some-user@email.com\",\n" +
	            "  \"password\": \"Passw0rd123!\" \n}";

		given().
			auth().
			basic("username", "password").
			header("Content-Type","application/json").
			contentType(ContentType.JSON).  //Request should be sent in JSON format
			accept(ContentType.JSON).       //Response should be received in JSON format
			body(validRequest).
		when().
			post("/users").
		then().
			statusCode(201).
			log().all();
	}
	
	@Test
	// OAuth is technically an authorization framework, and it doesn't define any mechanism for authenticating user.
	// REST Assured allows configuring the OAuth 2.0 access token to request a secured resource.
	public void testPostWithOuthAuthorization()
	{	

		baseURI = "http://localhost:8080";

		String validRequest = "{\n" +
	            "  \"username\": \"some-user\",\n" +
	            "  \"email\": \"some-user@email.com\",\n" +
	            "  \"password\": \"Passw0rd123!\" \n}";

		given().
			auth().
			oauth2("accessToken").
			header("Content-Type","application/json").
			contentType(ContentType.JSON).  //Request should be sent in JSON format
			accept(ContentType.JSON).       //Response should be received in JSON format
			body(validRequest).
		when().
			post("/users").
		then().
			statusCode(201).
			log().all();
	}
}
