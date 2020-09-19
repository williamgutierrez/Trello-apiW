package trello.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public final class RequestManager {

    private static RequestSpecification requestSpecification = new Authentication().getRequestSpecification();

    public static Response get(String endpoint) {
        return given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .get(endpoint);
    }

    public static Response post(String endpoint, String body    ){
        return given()
                .spec(requestSpecification)
                .log().all()
                .body(body)
                .when()
                .post(endpoint);
    }
    public static Response post(String endpoint, Board board    ){
        return given()
                .spec(requestSpecification)
                .log().all()
                .body(board)
                .when()
                .post(endpoint);
    }


    public static Response put(String endpoint, String body    ){
        return given()
                .spec(requestSpecification)
                .log().all()
                .body(body)
                .when()
                .post(endpoint);
    }


    public static Response delete(String endpoint){
        return given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .delete(endpoint);
    }
}
