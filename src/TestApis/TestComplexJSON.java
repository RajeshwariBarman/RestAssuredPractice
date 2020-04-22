package TestApis;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class TestComplexJSON {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// print no of courses return by API 
		
		JsonPath js = new JsonPath(Payload.ComJson());
		int no_course = js.getInt("courses.size()");
		System.out.println(no_course);
		
		//print the title of the course (get & getstring both are same)
		String title = js.get("courses[0].title");
		System.out.println(title);
		
		
		//printing all the courses in JSON & cost of copies sold for each course
		for (int i = 0 ; i < no_course ; i++)
		{
			System.out.println(js.getString("courses["+i+"].title"));
			System.out.println(js.getInt("courses["+i+"].price")*
					js.getInt("courses["+i+"].copies"));
		}

	}

}
