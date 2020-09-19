package trello.api;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.ResponseAwareMatcher.*;
import static org.hamcrest.Matchers.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.junit.Assert.assertEquals;


public class TrelloApiTest {
    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;
    private String id;

    public TrelloApiTest(){
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://api.trello.com")
                .addHeader("Content-Type","application/json")
                .addQueryParam("key","bec082140704de2f1f2caa464ada9112")
                .addQueryParam("token","579af54c451930c0bd4e3432222971c491476d4a79f5cc6607376ecaa1953a16")
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }
    @Before
    public void init(){
     /* id =  given().
                spec(requestSpecification)
                .body("{\"name\":\"Example Board\"}")
                .log().all()
                .when()
                .post("/1/boards")
                .then()
                .log().all().
               spec(responseSpecification)
                .extract().response().path("id") ;*/
        id = RequestManager.post("/1/boards","{\"name\":\"Example Board\"}").path("id");

    }
    @After
    public void finalizer(){
       /* given().
                spec(requestSpecification).
                log().all().
                when().
                delete("/1/boards/{id}",id).
                then().
                log().all().
                spec(responseSpecification);*/
        RequestManager.delete("/1/boards/"+id);
    }
@Test
public void testTrelloBoardGetMethodById() {
    /*given().spec(requestSpecification)
            .log().all()
            .when()
            .get("/1/boards/{id}", id)
            .then()
            .log().all()
            .spec(responseSpecification);
*/
    //get("/1/boards/{id}/list/{listId}",id ,listid);
    // obteniendo 1 Id o id List]
    Response response = RequestManager.get("/1/boards/"+id);
  assertEquals(response.getStatusCode(),200);

}



    @Test
    public void testTrelloBoardsGetMethod(){
//        given()
//                .spec(requestSpecification)
//                .log().all()
//                .when()
//                .get("/1/members/me/boards")
//                .then().log().all().
//                spec(responseSpecification)
//        .body(matchesJsonSchemaInClasspath("validator.json"));
        Response response = RequestManager.get("/1/members/me/boards") ;
        response.then().body(matchesJsonSchemaInClasspath("validator.json"));

    }
    @Test
    public void testTrelloBoardsPostMethod(){
        given().
                spec(requestSpecification)
                .body("{\"name\":\"Example Board\"}")
                .log().all()
                .when()
                .post("/1/boards")
                .then().spec(responseSpecification)
                .assertThat()
                .body("name",equalTo("Example Board"));

    }

    @Test
    public void testTrelloBoardsPutMethod() {
        given().
                spec(requestSpecification).
                body("{\"name\":\"Renamed Example\"}").
                log().all().
                when().
                put("/1/boards/{id}","5f5eba58360df26ea61a359f").
                then().
                log().all().
                spec(responseSpecification);
        Response response= RequestManager.put("/1/boards/{id}","{\"name\":\"Renamed Example\"}");
        assertEquals(response.getStatusCode(),200 );
    }
    @Test
    public void testTrelloBoardsDeleteMethod() {
        given().
                spec(requestSpecification).
                log().all().
                when().
                delete("/1/boards/{id}","5f5eba58360df26ea61a359f").
                then().
                log().all().
                spec(responseSpecification);
    }



}
