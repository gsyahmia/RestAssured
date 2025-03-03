package practice_1;

import org.testng.annotations.Test;

//import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
/*
 * 
given()
	content type, set cookies, add auth, add param, set headers info, etc..

when()
	get,post,put,delete

then()
	validate status code, extract response, extract headers cookies & response body..
	
	
GET users
https://reqres.in/api/users?page=2/

POST users
https://reqres.in/api/users

body
{
    "name": "morpheus",
    "job": "leader"
}
PUT
https://reqres.in/api/users/2

{
    "name": "morpheus",
    "job": "zion resident"
}

DELETE 
https://reqres.in/api/users/[userid]
*/

public class HTTPRequest {

	int id ;
	@Test(priority = 1)
	void getUsers() {
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2/")
			
			
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
		
	}
	@Test(priority = 2)
	void createUser() {
		HashMap dataUsers = new HashMap();
		dataUsers.put("name", "capaa");
		dataUsers.put("job", "trainer");
		
		id=given()
			.contentType("application/json")
			.body(dataUsers)
		
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		
	}
	
	@Test(priority = 3, dependsOnMethods = "createUser")
	void updateUser() {
		
		HashMap updateUsers = new HashMap();
		updateUsers.put("name", "capaa1");
		updateUsers.put("job", "trainer2");
		
		given()
			.contentType("application/json")
			.body(updateUsers)
		
		
		.when()
			.put("https://reqres.in/api/users/"+id)
			
		
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority=4)
	void deleteUser() {
		
		given()
		
		.when()
			.delete("https://reqres.in/api/users/"+id)
		
		.then()
			.statusCode(204)
			.log().all();
	}
}
