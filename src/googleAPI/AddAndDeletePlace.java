package googleAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AddAndDeletePlace {
	public FileInputStream fis;
	public Properties prop;
	
	@BeforeTest
	public void Setup() throws IOException {
		fis=new FileInputStream("C:\\Users\\Vikash\\Desktop\\Java\\RestAssuredBasics\\src\\files\\env.properties");
		prop=new Properties();
		prop.load(fis);
	}
	
	@Test
	public void PostRequest() {
		String body="{"+
			    "\"location\":{"+
		        "\"lat\" : -38.383494,"+
		        "\"lng\" : 33.427362"+
		    "},"+
		    "\"accuracy\":50,"+
		    "\"name\":\"Frontline house\","+
		    "\"phone_number\":\"(+91) 983 893 3937\","+
		    "\"address\" : \"29, side layout, cohen 09\","+
		    "\"types\": [\"shoe park\",\"shop\"],"+
		    "\"website\" : \"http://google.com\","+
		    "\"language\" : \"French-IN\""+
		"}";		
		RestAssured.baseURI=prop.getProperty("Host");		
		
		//Grab the Response
		Response res=given().
			queryParam("key", " qaclick123").
			body(body).
		when().
			post("/maps/api/place/add/json").
		then().
			assertThat().statusCode(200).and().contentType(ContentType.JSON).
			body("status", equalTo("OK")).
			extract().response();
		
		//Convert into String
			String responseString=res.asString();
			JsonPath jspath=new JsonPath(responseString);
		//Grab the PlaceId	
			String placeId=jspath.get("place_id");
			System.out.println(placeId);
			
			String body2="{"+
				    "\"place_id\":\""+placeId+"\""+
			"}";
			System.out.println("Body2"+body2);
			
		//Delete the Place Id
			given().
				queryParam("key", " qaclick123").
				body(body2).
			post("/maps/api/place/delete/json").
			then().
				assertThat().statusCode(200).and().contentType(ContentType.JSON).
				body("status", equalTo("OK"));	
			

	}

}



