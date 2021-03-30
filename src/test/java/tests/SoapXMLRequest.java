package tests;

import static io.restassured.RestAssured.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import io.restassured.http.ContentType;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class SoapXMLRequest {

	@Test
	public void validateSoapXML() throws IOException, FileNotFoundException
	{
		baseURI = "http://www.dneonline.com";
		
		// For creating request body
		String projectPath = System.getProperty("user.dir");
		FileInputStream fileInputStream = new FileInputStream(new File(projectPath+"/src/test/resources/SoapRequest/add.xml"));
		String requestBody = IOUtils.toString(fileInputStream, "UTF-8");
		
		given().
			contentType("text/xml").        // SOAP Request should be sent in "text/xml" format
			accept(ContentType.XML).        // Response should be received in XML format
			body(requestBody).
		when().
			post("/calculator.asmx").
		then().
			statusCode(200).
			log().all().
		and().
			body("//*:AddResult.text()", equalTo("5"));
	}
}
