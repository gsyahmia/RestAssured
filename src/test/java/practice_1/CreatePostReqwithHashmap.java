package practice_1;

import java.util.HashMap;

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

public class CreatePostReqwithHashmap {

	// 1) Post request body using Hashmap
	@Test(priority=1)
	void testPostUsingHashMap() {
		
		HashMap data = new HashMap();
		
		data.put("name", "Hendry");
		data.put("location", "Genova");
		data.put("phone", "161611293802938");
		
		String courseArr[] = {"Java","C++"};
		data.put("courses", courseArr);
		
		
		given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post("http://localhost:3000/student")
		
		.then()
			.statusCode(201)
			.body("name",equalTo("Hendry"))
			.body("location",equalTo("Genova"))
			.body("phone",equalTo("161611293802938"))
			.body("courses[0]",equalTo("Java"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type", "application/json")
			.log().all();
	}
	
	//deleting student record
	@Test(priority=2)
	void testDelete() {
		given()
		
		.when()
			.delete("http://localhost:3000/student/d2fe")
			
		.then()
			.statusCode(200);
	}

}
