package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;
/*
Website to refer : https://www.guru99.com/rest-assured.html
*/
public class GetExampleUsingQueryParam {

	//@Test
	// GET requests are used to retrieve resource information only. 
	// In this method we are using queryParam to set the parameters passed in the URL
	public void testGetUsingQueryParam()
	{
		//Full URL: http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1
		
		given().
			queryParam("CUSTOMER_ID", "68195").
			queryParam("PASSWORD", "1234!").
			queryParam("Account_No", "1").
		when().
			get("http://demo.guru99.com/V4/sinkministatement.php").
		then().
			statusCode(200).  // Verifying status code of response is 200
			log().all();      // Retrieving headers as well as response body here
	}
	
	//@Test
	public void getResponseBody()
	{
		//Full URL: http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1
		
		given().
			queryParam("CUSTOMER_ID", "68195").
			queryParam("PASSWORD", "1234!").
			queryParam("Account_No", "1").
		when().
			get("http://demo.guru99.com/V4/sinkministatement.php").
		then().
			statusCode(200).
			log().body();     // Retrieving only response body here
	}
	
	//@Test
	public void getResponseHeaders()
	{
		//Full URL: http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1
		
		given().
			queryParam("CUSTOMER_ID", "68195").
			queryParam("PASSWORD", "1234!").
			queryParam("Account_No", "1").
		when().
			get("http://demo.guru99.com/V4/sinkministatement.php").
		then().
			log().headers();  // Retrieving only response headers here
	}
	
	//@Test
	// To fetch different parts of the response, the keyword 'extract' is very important.
	// Output like : Content type of the response is : text/html; charset=UTF-8
	public void getResponseContentType()
	{
		//Full URL: http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1
		System.out.println("Content type of the response is : "+
		given().
			queryParam("CUSTOMER_ID", "68195").
			queryParam("PASSWORD", "1234!").
			queryParam("Account_No", "1").
		when().
			get("http://demo.guru99.com/V4/sinkministatement.php").
		then().
			extract().contentType() );
	}
	
	//@Test
	// Output like : The time taken to fetch the response is : 1057 milliseconds
	public void getResponseTime()
	{
		//Full URL: http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1
		System.out.println("The time taken to fetch the response is : "+
		given().
			queryParam("CUSTOMER_ID", "68195").
			queryParam("PASSWORD", "1234!").
			queryParam("Account_No", "1").
		when().
			get("http://demo.guru99.com/V4/sinkministatement.php").
		then().
			extract().timeIn(TimeUnit.MILLISECONDS)+" milliseconds");
	}
	
	//@Test
	// This method does't work as of now due to result: in path which is somehow wrong
	public void getSpecificPartOfResponseBody(){

		ArrayList<String> amounts = given().
										queryParam("CUSTOMER_ID", "68195").
										queryParam("PASSWORD", "1234!").
										queryParam("Account_No", "1").
									when().
										get("http://demo.guru99.com/V4/sinkministatement.php").
									then().
									    extract().path("'result:'.statements.AMOUNT") ;
		
		int sumOfAll=0;
		for(String amount :amounts){

		    System.out.println("The amount value fetched is "+amount);
		    sumOfAll = sumOfAll + Integer.valueOf(amount);
		}
		System.out.println("The total amount is "+sumOfAll);
	}
}
