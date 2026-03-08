package api.endpoints;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.json.JSONObject;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

// UserEndPoints.java
// Created for perform CRUD operations Create, Read, Update, Delete for User module

public class UserEndPoints {
	
	// Create User
	public static Response createUser(User payload) {
		Response response =
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			.log().body()
		.when()
			.post(Routes.post_url);
		
		return response;
	}
	
	// Read User
	public static Response readUser(String username) {
		Response response =
		given()
			.pathParam("username", username)
		.when()
			.get(Routes.get_url);
		
		return response;
	}
	
	// Update User
	public static Response updateUser(String username, User payload) {
		Response response =
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(payload)
		.when()
			.put(Routes.update_url);
		
		return response;
	}
	
	// Delete User
	public static Response deleteUser(String username) {
		Response response =
		given()
			.pathParam("username", username)
		.when()
			.delete(Routes.delete_url);
		
		return response;
	}
}
