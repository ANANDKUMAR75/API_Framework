package api.endpoints;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.*;


//UserEndPoints.java
// Created for perform Create, Read, Update and Delete request 


public class UserEndPoints {


	static ResourceBundle getURL() 
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes");  // Load properties file
		return routes;	
	}

	public static Response createUser(User payload)
	{		
		String post_url=getURL().getString("post_url");

		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				//.post(Routes.post_url);	
				.post(post_url);	//end using from properties file using resourcebundle
		//			.post("https://petstore.swagger.io/v2/user");
		return response;
	}


	public static Response readUser(String userName)
	{
		Response response=given()
				.pathParam("username",userName)
				.when()
				.get(Routes.get_url);	
		return response;
	}



	public static Response updateUser(String userName, User payload)
	{
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
				.when()
				.put(Routes.update_url);
		return response;
	}


	public static Response deleteUser(String userName)
	{
		Response response=given()
				.pathParam("username", userName)			
				.when()
				.delete(Routes.delete_url);
		return response;
	}
}
