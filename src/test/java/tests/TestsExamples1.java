package tests;

//Importing static RestAssured package
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class TestsExamples1 {

	//Scenario for writing like : given when then 
	@Test
	public void test_2()
	{
		baseURI = "https://reqres.in/api";
		given().
			get("/users?page=2").
		then().
			statusCode(200).
			body("data[1].id",equalTo(8)).
			body("data.first_name",hasItems("Lindsay","Lindsay")).
			log().all();
	
		//Json Body was found through JSON Path Finder		
	}
}
