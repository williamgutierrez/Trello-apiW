package trello.api;

import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RedirectSpecification;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import io.restassured.http.ContentType;

public class ApiRestAssuredTest {
    //private RequestSpecBuilder requestSpecBuilder;
    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;


    public ApiRestAssuredTest() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in/")
                //    .addHeader("Authorization", "Bearer 253e4e72a1e5510b6899aa17cd8acdf0899a85faa4cf8967746f60bc5cdddf7d")
                .addQueryParam("key", "bec082140704de2f1f2caa464ada9112")
                .addQueryParam("token", "579af54c451930c0bd4e3432222971c491476d4a79f5cc6607376ecaa1953a16")
                .build();
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    /* @Test
     /*public void testGithubSimple(){
         given()
                 .spec(requestSpecification)
                 .when().get("/users/sphinix27").
                 then().assertThat().statusCode(200);
     }*/
    @Test
    public void testGoRestInGetMethod() {
        given()
                .spec(requestSpecification)
                .when()
                .get("/public-api/users")
                .then()
                .spec(responseSpecification);//   .assertThat().statusCode(200);
    }

    // post("\"Authorization\", \"Bearer 253e4e72a1e5510b6899aa17cd8acdf0899a85faa4cf8967746f60bc5cdddf7d\"").
    //no sirve este metodo porque no crea  cuando s elo envia por el post
    @Test
    public void testGoRestInPostMethod() {
        given().
                spec(requestSpecification)
                .body("{\"name\":\"william h Ramakrishna\", \"gender\":\"Male\", \"email\":\"gg.willima@15ce.com\", \"status\":\"Active\"}")
                .log().all().
                when().
                post("/public-api/users").
                then()
                .spec(responseSpecification)
                .assertThat()
                .body("code", equalTo(201));
    }

    @Test
    public void testGoRestInPostMethodWithForParams() {
        Response response = given()
                .spec(requestSpecification)

                .formParam("name", "william")
                .formParam("gender", "Male")
                .formParam("email", "rererm@gmail.com")
                .formParam("status", "Active").
                        when().
                        post("https://gorest.co.in/public-api/users").
                        then()
                .spec(responseSpecification)
                .extract().response();  //assertThat().statusCode(200);
        assertEquals("william", response.path("data.name"));
    }

    @Test
    public void testGoRestInPutMethod() {
        given()
                .spec(requestSpecification)

                .body("{\"name\":\"william h Ramakrishna\", \"gender\":\"Male\", \"email\":\"ree.willima@15ce.com\", \"status\":\"Active\"}").
                when().
                put("/public-api/users/1").
                then().
                spec(responseSpecification);   /*assertThat().statusCode(200)*/
        ;
    }

    @Test
    public void testGorestInDeleteMethod() {
        given()
                .spec(requestSpecification).

                when().
                delete("/public-api/users/1").
                then().
                spec(responseSpecification);
    }


}
