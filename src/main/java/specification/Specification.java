package specification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class Specification {

    public  static RequestSpecification requestSpec(String baseUri, String contentType) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType(contentType)
                .build();
    }

    public static ResponseSpecification responseSpec(int statusCode){
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }

    public static ResponseSpecification responseSpec(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static void initSpec(RequestSpecification requestSpec){
        RestAssured.requestSpecification = requestSpec;
    }

    public static void initSpec(ResponseSpecification responseSpec){
        RestAssured.responseSpecification = responseSpec;
    }

    public static void initSpec(RequestSpecification requestSpec, ResponseSpecification responseSpec){
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }

    public static void clearSpec() {
        RestAssured.requestSpecification = null;
        RestAssured.responseSpecification = null;
    }
}
