package googleAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class SearchPlace {

	@Test
	public void getRequest() {
		RestAssured.baseURI = "https://maps.googleapis.com";
		given().
			param("location", "-33.8670522,151.1957362").
			param("radius", "1500").
			param("type", "restaurant").
			param("keyword", "cruise").
			param("key", "AIzaSyDSUJDL8DDwkueiNtndtG66RNH7TZZr2EA").log().all().
		when().
			get("/maps/api/place/nearbysearch/json").
		then().
			assertThat().statusCode(200).contentType(ContentType.JSON).
			header("Server", "scaffolding on HTTPServer2").
			body("results[0].name", equalTo("Cruise Bar, Restaurant & Events")).log().body();

	}

}
