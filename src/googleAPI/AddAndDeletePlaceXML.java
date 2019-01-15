package googleAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class AddAndDeletePlaceXML {
	public FileInputStream fis;
	public Properties prop;
	
	@BeforeTest
	public void Setup() throws IOException {
		fis=new FileInputStream("C:\\Users\\Vikash\\Desktop\\Java\\RestAssuredBasics\\src\\files\\env.properties");
		prop=new Properties();
		prop.load(fis);
	}
	
	@Test
	public void PostRequest() throws IOException {
		String body=ReusableMethods.GenerateStringFromResource("C:\\Users\\Vikash\\Desktop\\Java\\RestAssuredBasics\\src\\files\\Request.xml");;		
		RestAssured.baseURI=prop.getProperty("Host");		
		
		//Grab the Response
		Response res=given().
			queryParam("key", " qaclick123").
			body(body).
		when().
			post("/maps/api/place/add/xml").
		then().
			assertThat().statusCode(200).and().contentType(ContentType.XML).
			body("response.status", equalTo("OK")).
			extract().response();
		
			XmlPath xml=ReusableMethods.ResponsetoXMLPATH(res);	
	
		//Grab the PlaceId	
			String placeId=xml.get("response.place_id");
			System.out.println(placeId);
			
			String body2="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + 
					"<root>\r\n" + 
					"    <place_id>"+placeId+"</place_id>\r\n" + 
					"</root>";
			
		//Delete the Place Id
			given().
				queryParam("key", " qaclick123").
				body(body2).
			post("/maps/api/place/delete/xml").
			then().
				assertThat().statusCode(200).and().contentType(ContentType.XML).
				body("response.status", equalTo("OK"));	
			

	}


}



