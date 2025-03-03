package practice_1;

import java.util.HashMap;

import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
/*
1) Post request body using Hashmap
2) Post request body creation using Org.JSON
2) Post request body creation using POJO Class
2) Post request using external json file data
*
*/

public class CreatePostReqwithJsonOrg {

	// 1) Post request body using Org.Json library
	@Test(priority=1)
	void testPostUsingOrgJson() {
		
		JSONObject data = new JSONObject();
		
		
		data.put("name", "Putri");
		data.put("location", "Alaska");
		data.put("phone", "02732723");
		
		String courseArr[] = {"Java","Python"};
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json")
			.body(data.toString())
			
		.when()
			.post("http://localhost:3000/student")
		
		.then()
			.statusCode(201)
			.body("name",equalTo("Putri"))
			.body("location",equalTo("Alaska"))
			.body("phone",equalTo("02732723"))
			.body("courses[0]",equalTo("Java"))
			.body("courses[1]",equalTo("Python"))
			.header("Content-Type", "application/json")
			.log().all();
	}
	
	//deleting student record
	//@Test(priority=2)
	void testDelete() {
		given()
		
		.when()
			.delete("http://localhost:3000/student/c13a")
			
		.then()
			.statusCode(200);
	}

}
