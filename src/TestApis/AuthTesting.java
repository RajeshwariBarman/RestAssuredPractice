package TestApis;
import static io.restassured.RestAssured.given;

import java.util.List;

import Pojo.GetCourse;
import Pojo.webAutomation;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class AuthTesting {

	public static void main(String[] args) throws InterruptedException {
//		// TODO Auto-generated method stub
//		System.setProperty("webdriver.chrome.driver","C:\\Users\\Rajeshwari\\Downloads\\chromedriver_win32_latest\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(4000,TimeUnit.MILLISECONDS);
//		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com"
//				+ "/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/"
//				+ "auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps."
//				+ "googleusercontent.com&response_type=code&redirect_uri=https://"
//				+ "rahulshettyacademy.com/getCourse.php&state =abcd");
//		
//		driver.findElement(By.xpath("//input[@type = 'email']")).sendKeys("");
//		driver.findElement(By.xpath("//input[@type = 'email']")).sendKeys(Keys.ENTER);
//		driver.findElement(By.xpath("//input[@type = 'password']")).sendKeys("");
//		driver.findElement(By.xpath("//*[@id = 'passwordNext']/descendant::span[text()= 'Next']")).click();
//		
//		//getting the code 
		Thread.sleep(5000);
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2FygEfeiS-R0mLvfrzWzBC9iw1-Y2m4SY-ol3yU-6chFqAfNTtLC0q_3vbNIcgwpBnLK3NYaGAkzdJCuciPhk3nXw&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none#";
		System.out.println(url);
		
		//getting the code from the browser
		
		String partial_code = url.split("code=")[1];
		String code = partial_code.split("&scope")[0];
		System.out.println(code);
		
		//getting the access token 
		String access_token_resp = given().urlEncodingEnabled(false).queryParam("code","4%2FywGNCo22xa5gbmaLF84ame4dxdSuaqazjdNm5i25j68DjGmciWBO-LL__kjyoi5E6_KA_XEA6Dud-BqTJVJ9UZw").
		queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
		queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type", "authorization_code").
		 queryParam("state", "verifyfjdss").when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		System.out.println(access_token_resp);
		
		//parsing the access token
		JsonPath js = new JsonPath(access_token_resp);
		String access_token = js.get("access_token");
		
		System.out.println("Token" +access_token);
		
		// getting the resource
		GetCourse resp = given().queryParams("access_token", access_token).
				expect().defaultParser(Parser.JSON).when()
      .get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
		

		
		System.out.println(resp.getInstructor());
		System.out.println(resp.getLinkedIn());
		System.out.println(resp.getCourses().getApi().get(0).getCourseTitle());
		
		
		// fetching the value of the "SoapUI Webservices testing" price : 
		
		int no_courses = resp.getCourses().getApi().size();
		System.out.println("course = "+no_courses);
		for(int i = 0 ; i < no_courses ; i++)
		{
			System.out.println("course name = " + resp.getCourses().getApi().get(i).getCourseTitle());
			if(resp.getCourses().getApi().get(i).getCourseTitle() .equals("SoapUI Webservices testing"))
			{
				System.out.println("Price = " + resp.getCourses().getApi().get(i).getPrice());
			}
			
		}
		
		// getting all the course of webautomation: 
		
		List<webAutomation> web = resp.getCourses().getWebAutomation();
		for(int i = 0 ; i < web.size() ; i++)
			System.out.println(web.get(i).getCourseTitle());
		
		
		
		

	}

}
