package files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {
	
	public static XmlPath ResponsetoXMLPATH(Response response) {
		String responseString=response.asString();
		XmlPath xmpath=new XmlPath(responseString);
		return xmpath;
	}
	
	public static JsonPath ResponsetoJSONPATH(Response response) {
		String responseString=response.asString();
		JsonPath xmpath=new JsonPath(responseString);
		return xmpath;
	}
	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}

}
