package TestApis;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class TestHashMapJSON {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String,Object> addplace = new HashMap<>();
		HashMap<String, Object> location = new HashMap<>();
		ArrayList<Object> type = new ArrayList<>();
		location.put("lat",38.383494);
		location.put("lng",33.427362);
		type.add("shoe park");
		type.add("shop");
		addplace.put("accuracy",50);
		addplace.put("name","Frontline house");
		addplace.put("phone_number","(+91) 983 893 3937");
		addplace.put("address","29, side layout, cohen 09");
		addplace.put("website","http://google.com");
		addplace.put("language","French-IN");
		addplace.put("types", type);
		addplace.put("location", location);
		
		//System.out.println(addplace);
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		Response resp = given().log().all().queryParam("key", "qaclick123").body(addplace).
				when().log().all().post("/maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).extract().response();
		
		String responsebody = resp.asString();
		System.out.println(responsebody);
		
		

	}

}
