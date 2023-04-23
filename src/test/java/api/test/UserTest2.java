package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest2 {

	Faker faker;
	User userPayLoad;
	public Logger logger;
	
	@BeforeClass
	public void setup()
	{
		faker=new Faker();
		userPayLoad=new User();
		
		userPayLoad.setId(faker.idNumber().hashCode());
		userPayLoad.setUsername(faker.name().username());
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		userPayLoad.setPassword(faker.internet().password(5,10));
		userPayLoad.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("****************Creating User******************");
		Response response=UserEndpoints2.createUser(userPayLoad);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("****************Reading User Info******************");
		Response response=UserEndpoints2.readUser(this.userPayLoad.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority=3)
	public void testUpdateUser()
	{
		//Update data using same payload
		
		logger.info("****************Updating User******************");
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndpoints2.updateUser(this.userPayLoad.getUsername(), userPayLoad);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		System.out.println("Checking after updating data: ");
		Response responseAfterUpdate=UserEndpoints2.readUser(this.userPayLoad.getUsername());
		response.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
	}
	
	@Test(priority=4)
	public void testDeleteUser()
	{
		logger.info("****************Deleting User******************");
		Response response=UserEndpoints2.deleteUser(userPayLoad.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
}
