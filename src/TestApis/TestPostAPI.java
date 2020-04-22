package TestApis;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.Payload;

public class TestPostAPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Adding a place 
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String output = 
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(Payload.data()).when().post("maps/api/place/add/json").then()
		       .assertThat().statusCode(200).body("scope", equalTo("APP")).extract().
		       response().asString();
		
		System.out.println(output);
	
		//for parsing the JSON 
		JsonPath path = new JsonPath(output);
		String place_id = path.getString("place_id");
		System.out.println(place_id);
			
		
		//updating the place: 
		
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
		body("{\r\n" + 
				"\"place_id\":\""+place_id+"\",\r\n" + 
				"\"address\":\"70 winter walk, USA\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").when().put("maps/api/place/update/json").then().
		assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
	  //Get Place
//		
	String getPlaceResponse  =	given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id",place_id)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
	
	path = new JsonPath(getPlaceResponse);
	String actual_address = path.getString("address");
	System.out.println(actual_address);
	Assert.assertEquals(actual_address , "70 winter walk, USA");
	
	
	

	}

}
