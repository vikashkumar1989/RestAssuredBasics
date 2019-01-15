package googleAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class SearchPlaceXML {

	@Test
	public void getRequest() {
		RestAssured.baseURI = "https://maps.googleapis.com";
		given().
			param("query", "restaurants+in+Sydney").
			param("key", "AIzaSyDSUJDL8DDwkueiNtndtG66RNH7TZZr2EA").
		when().
			get("/maps/api/place/textsearch/xml").
		then().
			assertThat().statusCode(200).contentType(ContentType.XML).
			header("Server", "scaffolding on HTTPServer2");
			

	}

}
