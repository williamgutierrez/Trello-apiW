package trello.api;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Authentication {

    private static final String KEY="bec082140704de2f1f2caa464ada9112";
    private static String BASE_URL="https://api.trello.com";
    private static final String TOKEN= "579af54c451930c0bd4e3432222971c491476d4a79f5cc6607376ecaa1953a16";
    private RequestSpecification requestSpecification;



    public Authentication(){
        requestSpecification = new RequestSpecBuilder()
        .setBaseUri(BASE_URL)
                .addHeader("Content-Type","application/json")
                .addQueryParam("key",KEY)
                .addQueryParam("token",TOKEN)
                .build();
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }
}
