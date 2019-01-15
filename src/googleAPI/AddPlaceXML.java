package googleAPI;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class AddPlaceXML {

	@Test
	public void PostRequest() throws IOException {
		String body=GenerateStringFromResource("C:\\Users\\Vikash\\Desktop\\Java\\RestAssuredBasics\\src\\files\\Request.xml");
		
		RestAssured.baseURI="http://216.10.245.166";		
		given().
			queryParam("key", " qaclick123").
			body(body).
		when().
			post("/maps/api/place/add/xml").
		then().
			assertThat().statusCode(200).and().contentType(ContentType.XML);			
		

	}
	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}

}
