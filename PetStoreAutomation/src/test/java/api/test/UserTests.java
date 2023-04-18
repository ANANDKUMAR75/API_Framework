package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User UserPayload;

	@BeforeClass
	public void SetupData()	{

		faker = new Faker();
		UserPayload = new User();

		UserPayload.setId(faker.idNumber().hashCode());
		UserPayload.setUsername(faker.name().username());
		UserPayload.setFirstName(faker.name().firstName());
		UserPayload.setLastName(faker.name().lastName());
		UserPayload.setEmail(faker.internet().safeEmailAddress());
		UserPayload.setPassword(faker.internet().password(5, 10));
		UserPayload.setPhone(faker.phoneNumber().cellPhone());
	}
		
		@Test(priority=1)
		public void testPostUser() {

			Response response=UserEndPoints.createUser(UserPayload);
			response.then().log().all();
			
			Assert.assertEquals(response.statusCode(),200);
		}
		
		
		@Test(priority=2)
		public void testGetUserByName()
		{
			Response response=UserEndPoints.readUser(this.UserPayload.getUsername());
			response.then().log().all();
			
			Assert.assertEquals(response.statusCode(),200);			
		}	
		
		
		@Test(priority=3)
		public void testUpdateUserByName()
		{
			
			UserPayload.setFirstName(faker.name().firstName());
			UserPayload.setLastName(faker.name().lastName());
			UserPayload.setEmail(faker.internet().safeEmailAddress());
										
			Response response=UserEndPoints.updateUser(this.UserPayload.getUsername(), UserPayload);
			response.then().log().all();
			
			Assert.assertEquals(response.statusCode(),200);	
			
			//Checking data after updates
			
			Response responseafterupdate=UserEndPoints.readUser(this.UserPayload.getUsername());
			responseafterupdate.then().log().all();
			Assert.assertEquals(responseafterupdate.statusCode(),200);				
		}	
		
		
		@Test(priority=4)
		public void testDeleteUser() {
			
			Response response=UserEndPoints.deleteUser(this.UserPayload.getUsername());
			Assert.assertEquals(response.statusCode(),200);
		
		}
			
	}

