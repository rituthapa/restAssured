package restAssured;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*; 
import io.restassured.matcher.RestAssuredMatchers.*; 
import static org.hamcrest.CoreMatchers.*;

import java.util.HashMap;
import java.util.Map; 

public class TEST_REQRES {

	@Test
	public void test_1() {
		given()
		.get("https://reqres.in/api/users?page=2")
		.then()
		.statusCode(200).assertThat()
		.body("data.id[0]", equalTo(7)).log().all();
		
	}
	
	@Test
	public void test_2() {
		given()
		.get("https://reqres.in/api/users?page=2")
		.then()
		.statusCode(200).assertThat()
		.body("data.first_name", hasItems("Michael","Rachel")).log().all();
	}
	@Test
	public void test_3() {
		//to create a user, for POST request, need a payload to pass
		//so create  a payload first, for this 
				//first create an object of hashmap class
				//add properties and values to it (map.put("name","ritu")
				//then, change it to JSON format by creating objet of JSONObject class
				//in rest assured libraries, this json data should be passed as a single string using .toJSONString method
		//now you write the given then testcase where body should be passed before the post in given()
		HashMap <String,Object> map = new HashMap <String, Object> ();
		map.put("name","ritu");
		map.put("gender","female");
		map.put("age", "27");
		System.out.println(map);
		
		JSONObject payload= new JSONObject();
		payload.put("name","ritu");
		payload.put("gender","female");
		payload.put("age","27");
		payload.toJSONString(map);
		
		given()
		.body(payload.toJSONString(map))
		.post("https://reqres.in/api/users")
		.then()
		.statusCode(201).assertThat()
		.body("name", hasItems("ritu")).log().all();
	}
	@Test
	public void test_4() {
		HashMap<String,Object> map= new HashMap<String,Object>();
		map.put("name","sanjok");
		map.put("gender","male");
		map.put("age", "27");
		JSONObject payload = new JSONObject();
		payload.put("name","sanjok");
		payload.put("gender","male");
		payload.put("age","27"); //you want to update name and gender and not the age, do you still add this or not?
		payload.toJSONString(map);
		
		given()
		.body(payload.toJSONString(map))
		.put("https://reqres.in/api/users/2") //you pass the user id to update ; that's the difference in put vs post in this example
		.then()
		.statusCode(200);
	
	}
	
	@Test
	public void test_5() {
		given()
		.delete("https://reqres.in/api/users/3")
		.then()
		.statusCode(204);
	
	}
	
}
