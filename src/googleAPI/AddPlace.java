package googleAPI;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class AddPlace {

	@Test
	public void PostRequest() {
		String body="{"+
			    "\"location\":{"+
		        "\"lat\" : -38.383494,"+
		        "\"lng\" : 33.427362"+
		    "},"+
		    "\"a ccuracy\":50,"+
		    "\"name\":\"Frontline house\","+
		    "\"phone_number\":\"(+91) 983 893 3937\","+
		    "\"address\" : \"29, side layout, cohen 09\","+
		    "\"types\": [\"shoe park\",\"shop\"],"+
		    "\"website\" : \"http://google.com\","+
		    "\"language\" : \"French-IN\""+
		"}";
		
		RestAssured.baseURI="http://216.10.245.166";		
		given().
			queryParam("key", " qaclick123").
			body(body).
		when().
			post("/maps/api/place/add/json").
		then().
			assertThat().statusCode(200).and().contentType(ContentType.JSON).
			body("status", equalTo("OK"));
		

	}

}
