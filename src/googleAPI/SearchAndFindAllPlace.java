package googleAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import files.ReusableMethods;

public class SearchAndFindAllPlace {

	@Test
	public void getRequest() {
		RestAssured.baseURI = "https://maps.googleapis.com";
		Response res=given().
			param("location", "-33.8670522,151.1957362").
			param("radius", "1500").
			param("type", "restaurant").
			param("keyword", "cruise").
			param("key", "AIzaSyDSUJDL8DDwkueiNtndtG66RNH7TZZr2EA").
		when().
			get("/maps/api/place/nearbysearch/json").
		then().
			assertThat().statusCode(200).contentType(ContentType.JSON).
			header("Server", "scaffolding on HTTPServer2").
			body("results[0].name", equalTo("Cruise Bar, Restaurant & Events")).
			extract().response();
			
			JsonPath js=ReusableMethods.ResponsetoJSONPATH(res);			
			int i=js.get("results.size()");
			System.out.println(i);
			
			for(int j=0;j<i;j++) {
				String s=js.get("results["+j+"].name");
				System.out.println(s);
			}
			

	}

}
