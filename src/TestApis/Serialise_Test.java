package TestApis;
import static io.restassured.RestAssured.given;
import java.util.ArrayList;

import Serialise_Pojo.AddPlace;
import Serialise_Pojo.Location;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;


public class Serialise_Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// setting the value for creating respone body JSON using POJO
		AddPlace add_place = new AddPlace();
		add_place.setAccuracy(50);
		add_place.setAddress("29, side layout, cohen 09");
		add_place.setLanguage("French-IN");
		add_place.setName("Frontline house");
		add_place.setPhone_number("(+91) 983 893 3937");
		add_place.setWebsite("http://google.com");
		ArrayList<String> my_list = new ArrayList<String>();
		my_list.add("shoe park");
		my_list.add("shop");
		add_place.setTypes(my_list);
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		add_place.setLocation(loc);
		
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		Response resp = given().log().all().queryParam("key", "qaclick123").body(add_place).
				when().log().all().post("/maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).extract().response();
		
		String responsebody = resp.asString();
		System.out.println(responsebody);
		Headers allhead = resp.getHeaders();
		
		for(Header header : allhead)
		{
			
			System.out.println("Header Name " + header.getName());
			System.out.println("Header Value " +header.getValue());
			
		}
		
//		System.out.println(resp.getHeader("Access-Control-Max-Age"));
//		System.out.println(resp.getHeaders());
//		System.out.println(resp.headers());
		
		
		System.out.println(resp.header("Access-Control-Max-Age"));
		
		

		
	}

}
