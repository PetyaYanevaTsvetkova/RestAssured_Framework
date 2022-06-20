import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.apache.groovy.util.Maps;

import java.util.List;
import java.util.Map;

public class myFirstRestAssuredClass {
    final static String URL = "https://api.publicapis.org/";

    public static void main(String[] args) {
        RestAssured.baseURI = URL;


        getResponseStatus();


        getResponseHeaders();


        getResponseHeader("Content-Type");


        Response responseBody = getResponseBodyAll();
        System.out.println(responseBody.body().prettyPrint());


        Response requestWithParams = getRequestWithParams(Maps.of(
                "title", "cat",
                "description", "Rwanda"));
        requestWithParams.body().prettyPrint();


        Response responseBodyJSON = getResponseBodyJSON(Maps.of(
                "Content-type", "application/json",
                "Server", "Caddy"));
        responseBodyJSON.body().prettyPrint();
    }



    private static <K, V> Response getRequestWithParams(Map map) {
        System.out.println("Response with parameters: ");
        return RestAssured.given()
                .with()
                .queryParams(map)
                .when()
                .get("entries");
    }

    private static <K, V> Response getResponseBodyJSON(Map map) {
        System.out.println("Response with headers: ");
        return RestAssured
                .given()
                .headers(map)
                .when()
                .get("categories");
    }


    private static void getResponseHeader(String headerKey) {
        System.out.println("Header with key " + headerKey + ": ");
        System.out.print(RestAssured
                .given()
                .when()
                .get(URL)
                .getHeader(headerKey));
        System.out.println();
        System.out.println();
    }

    private static void getResponseHeaders() {
        System.out.println("Get Response Headers: ");
        for (Header header : RestAssured
                .given()
                .when()
                .get(URL)
                .getHeaders()) {
            System.out.println(header);
        }
        System.out.println();
    }

    public static void getResponseStatus() {
        int statusCode = RestAssured
                .given()
                .when()
                .get(URL)
                .getStatusCode();
        System.out.println("The response status is: " + statusCode);
        RestAssured.given().when().get(URL).then().assertThat().statusCode(200);
        System.out.println();
    }

    public static Response getResponseBodyAll() {
        System.out.println("All records: ");
        return RestAssured
                .given()
                .when()
                .get("entries");
    }
}
