package tests;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
public class validateResponseAgainstXMLSchema {

	@Test
	public void XMLSchemaValidation() throws IOException, FileNotFoundException
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
			body("//*:AddResult.text()", equalTo("5")).
		// Specify the path of the XML schema to be verified present at /target/classes folder
		and().
			assertThat().body(matchesXsdInClasspath("calculator.xsd"));
	}
}
