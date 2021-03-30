package tests;

import static io.restassured.RestAssured.*;
// Import to be done for JSON schema validation
import static io.restassured.module.jsv.JsonSchemaValidator.*; 
import org.testng.annotations.Test;

public class validateJSONagainstSchema {

	@Test
	public void JSONSchemaValidation()
	{
		baseURI = "https://reqres.in/api";
		
		when().
			get("users?page=2").
		then().
			// Specify the path of the JSON schema to be verified present at /target/classes folder
			assertThat().body(matchesJsonSchemaInClasspath("schema.json")). 
			statusCode(200);
	}
}
