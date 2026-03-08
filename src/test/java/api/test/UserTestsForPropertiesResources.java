package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPointsForPropertiesResources;
import api.payload.User;
import io.restassured.response.Response;

public class UserTestsForPropertiesResources {
	
	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.number().digits(10));
		
		// Logs
		logger=LogManager.getLogger(this.getClass());
		logger.debug("debuging");
	}
	
	@Test(priority=1)
	public void testPostUser() {
		// Logging the user payload details
		logger.info("********** Creating user **********");
		// Write code to send POST request to create user using userPayload
		Response response=UserEndPointsForPropertiesResources.createUser(userPayload);
		response.then().log().all();
		
		// Validate the response status code and other assertions as needed
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Logging the response details
		logger.info("********** User created successfully **********");
	}
	
	@Test(priority=2)
	public void testGetUserByUsername() {
		// Logging the username being retrieved
		logger.info("********** Reading user info ***********");
		
		Response response=UserEndPointsForPropertiesResources.readUser(userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		// Logging the username was retrieved
		logger.info("********** User info displayed ***********");
	}
	
	@Test(priority=3)
	public void testUpdateUser() {
		// Logging the username being updated
		logger.info("********** Updating user info ***********");
		
		// Update user details in userPayload as needed
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPointsForPropertiesResources.updateUser(userPayload.getUsername(), userPayload);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		// Checking data after update
		Response getResponse=UserEndPointsForPropertiesResources.readUser(userPayload.getUsername());
		Assert.assertEquals(getResponse.getStatusCode(), 200);
		
		// Logging the username was updated
		logger.info("********** User info updated ***********");
	}
	
	@Test(priority=4)
	public void testDeleteUser() {
		// Logging the username being deleted
		logger.info("********** Deleting user ***********");
		
		// Write code to send DELETE request to delete user using username from userPayload
		Response response=UserEndPointsForPropertiesResources.deleteUser(userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		// Checking data after delete
		Response getResponse=UserEndPointsForPropertiesResources.readUser(userPayload.getUsername());
		Assert.assertEquals(getResponse.getStatusCode(), 404);
		
		// Logging the username was deleted
		logger.info("********** User deleted ***********");
	}
}
