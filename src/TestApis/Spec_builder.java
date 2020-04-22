package TestApis;
import static io.restassured.RestAssured.given;
import java.util.ArrayList;

import Serialise_Pojo.AddPlace;
import Serialise_Pojo.Location;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class Spec_builder {

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
		
		RequestSpecification req_spec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addQueryParam("key", "qaclick123").setContentType("application/json").build();
		
		RequestSpecification req = given().spec(req_spec).body(add_place);
		
		ResponseSpecification resp_spec = new ResponseSpecBuilder().expectStatusCode(200).
				expectContentType("application/json").build();
		
		
		Response resp = req.when().post("/maps/api/place/add/json").then().log().all().spec(resp_spec)
				.extract().response();
		
		
		String responsebody = resp.asString();
		System.out.println(responsebody);

		
	}

}
