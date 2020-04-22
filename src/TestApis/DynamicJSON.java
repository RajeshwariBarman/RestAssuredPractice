package TestApis;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJSON {
	
	@Test(dataProvider = "bookdata")
	public void addbook(String isbn , String aisle)
	{
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type","application/json").
				body(Payload.addBookJSON(isbn,aisle)).post("Library/Addbook.php").then().log().all().assertThat().
				statusCode(200).extract().asString();
		
		JsonPath js = new JsonPath(response);
		String id = js.getString("ID");
		
		// delete the data
		given().header("Content-Type","application/json").
				body("{\r\n" + 
						"    \"ID\": \""+id+"\"\r\n" + 
						"}").
				when().delete("Library/DeleteBook.php").then().log().all().assertThat().
				statusCode(200).extract().asString();
		
		
	}
	
	@DataProvider(name = "bookdata")
	public Object[][] getdata()
	{
		return new Object[][] {{"abbb", "235"}, {"d3hj","889"}};
	}

}
