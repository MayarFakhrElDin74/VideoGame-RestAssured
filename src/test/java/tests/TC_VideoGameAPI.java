package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.response.Response;



@Test (priority = 1)


public class TC_VideoGameAPI {

	public void test_GetAllVideoGames ()

	{
		given()
		.when()
		.get("http://localhost:8080/app/videogames")
		.then()
		.statusCode(200) ;
	}



	@Test (priority = 2)


	public void test_AddNewVideoGame ()

	{
		HashMap <String, String> data = new HashMap <String, String> () ;

		data.put("id", "100") ;
		data.put("name", "Spider-Man") ;
		data.put("releaseDate", "2023-04-06T22:12:55.318Z") ;
		data.put("reviewScore", "5") ;
		data.put("category", "Adventure") ;
		data.put("rating", "Universal") ;	

		Response res = 

				given()
				.contentType("application/json")
				.body(data)

				.when()
				.post("http://localhost:8080/app/videogames") 

				.then().
				statusCode(200).
				log().body()
				.extract().response() ;

		String jsonString = res.asString() ;
		assertEquals(jsonString.contains("Record Added Successfully"), true);

	}

	
	
	@Test (priority = 3)

	public void test_GetVideoGameById ()

	{
		given()
		.when()
		.get("http://localhost:8080/app/videogames/100") 

		.then()
		.statusCode(200)
		.body("VideoGame.id", equalTo("100"))
		.body("VideoGame.name", equalTo("Spider-Man")) ;

	}
	
	

	@Test (priority = 4)

	public void test_UpdateVideoGame ()

	{
		HashMap <String, String> data = new HashMap <String, String> () ;

		data.put("id", "100") ;
		data.put("name", "Super-Man") ;
		data.put("releaseDate", "2023-04-06T22:12:57.318Z") ;
		data.put("reviewScore", "4") ;
		data.put("category", "Adventure") ;
		data.put("rating", "Universal") ;

		given()
		.contentType("application/json") 
		.body(data)

		.when()
		.put("http://localhost:8080/app/videogames/100") 

		.then()
		.statusCode(200)
		.log().body()
		.body("VideoGame.id", equalTo("100"))
		.body("VideoGame.name", equalTo("Super-Man")) ;

	}

	
	
	@Test (priority = 5)

	public void test_DeleteVideoGame ()

	{
		Response res =

				given()
				.when()
				.delete("http://localhost:8080/app/videogames/100")

				.then()
				.statusCode(200)
				.log().body() 
				.extract().response() ;

		String jsonString = res.asString() ;
		assertEquals(jsonString.contains("Record Deleted Successfully"), true) ;

	}


}
