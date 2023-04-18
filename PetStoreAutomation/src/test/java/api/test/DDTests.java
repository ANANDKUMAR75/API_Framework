package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import io.restassured.response.Response;
import api.utilities.DataProviders;
import api.utilities.XLUtility;

public class DDTests {

	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)

	public void testPostUser (String UserID, String userName, String Fname, String lname, String useremail, String pwd, String Ph) 

	{
		User userPayload = new User();

		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(Ph);

		Response response=UserEndPoints.createUser(userPayload);		
		Assert.assertEquals(response.statusCode(),200);
	}


	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)

	public void testgetByUsername(String userName)
	{
		Response response=UserEndPoints.readUser(userName);
		Assert.assertEquals(response.getStatusCode(),200);
	}



	@Test(priority=3, dataProvider="UserNames", dataProviderClass=DataProviders.class)

	public void testDeleteUserByName(String userName)
	{
		Response response=UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(),200);
	}






}