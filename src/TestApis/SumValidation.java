package TestApis;

import org.testng.Assert;
import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	public static void main(String [] args)
	{
		sumOfCourses();
	}
	
	@Test
	public static void  sumOfCourses()
	{
		JsonPath js = new JsonPath(Payload.ComJson());
		int no_of_courses = js.getInt("courses.size()");
		int sum = 0 ; 
		for(int i = 0 ; i < no_of_courses ; i++)
		{
			sum = sum + (js.getInt("courses["+i+"].price")*
					js.getInt("courses["+i+"].copies"));
		}
		
		System.out.println("sum = " +sum);
		Assert.assertEquals(sum,js.getInt("dashboard.purchaseAmount"));
	}

}
