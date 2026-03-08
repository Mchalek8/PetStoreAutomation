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

import java.util.ResourceBundle;

// UserEndPoints.java
// Created for perform CRUD operations Create, Read, Update, Delete for User module

public class UserEndPointsForPropertiesResources {
	
	// Read the URL from properties file
	static ResourceBundle getURL() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("routes"); // "routes" is the name of the properties file in resources
		return resourceBundle;
	}
	
	// Create User
	public static Response createUser(User payload) {
		
		String post_url=getURL().getString("post_url");
		
		Response response =
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			.log().body()
		.when()
			.post(post_url);
		
		return response;
	}
	
	// Read User
	public static Response readUser(String username) {
		
		String get_url=getURL().getString("get_url");
		
		Response response =
		given()
			.pathParam("username", username)
		.when()
			.get(get_url);
		
		return response;
	}
	
	// Update User
	public static Response updateUser(String username, User payload) {
		
		String update_url=getURL().getString("update_url");
		
		Response response =
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(payload)
		.when()
			.put(update_url);
		
		return response;
	}
	
	// Delete User
	public static Response deleteUser(String username) {
		
		String delete_url=getURL().getString("delete_url");
		
		Response response =
		given()
			.pathParam("username", username)
		.when()
			.delete(delete_url);
		
		return response;
	}
}
