package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import api.endpoints.*;

import api.payload.User;
import api.utilities.*;
import io.restassured.response.Response;

public class DDTests {
	
	
	DataProviders data;
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testMultipleCreateUser(String userID,String uname, String fname,String lname,String uemail,String pwd,String phone)
	{
		User userPayLoad=new User();
		userPayLoad.setId(Integer.parseInt(userID));
		userPayLoad.setUsername(uname);
		userPayLoad.setFirstName(fname);
		userPayLoad.setLastName(lname);
		userPayLoad.setEmail(uemail);
		userPayLoad.setPassword(pwd);
		userPayLoad.setPhone(phone);
		
		Response response=UserEndPoints.createUser(userPayLoad);
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName)
	{
		Response res=UserEndPoints.deleteUser(userName);
		Assert.assertEquals(res.getStatusCode(),200);
	}
}


