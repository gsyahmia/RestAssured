package practice_2;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class GetDynamicCookies {

//	@Test
	void getCookies() {
		given()
		
		.when()
			.get("https://www.google.com/")
		
		.then()
			.log().all();
	}
	
	@Test
	void getCookiesDynamic() {
		
		Response res = given()
		
		.when()
			.get("https://www.google.com/");
		
		String cookie_value = res.getCookie("AEC");
		System.out.println("cookie_value == "+cookie_value);
	}
}
