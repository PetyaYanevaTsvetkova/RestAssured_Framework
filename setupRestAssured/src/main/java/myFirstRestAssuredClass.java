import io.restassured.RestAssured;
import io.restassured.http.Header;

public class myFirstRestAssuredClass {
    final static String URL = "https://www.abv.bg/";

    public static void main(String[] args) {
        getResponseStatus();
        getResponseHeaders();
        getResponseHeader();
        getResponseBody();
    }

    private static void getResponseHeader() {
        System.out.println(RestAssured
                .given()
                .when()
                .get(URL)
                .getHeader("Server"));
    }

    private static void getResponseHeaders() {
        for (Header header : RestAssured
                .given()
                .when()
                .get(URL)
                .getHeaders()) {
            System.out.println(header);
        }
    }

    public static void getResponseStatus() {
        int statusCode = RestAssured
                .given()
                .when()
                .get(URL)
                .getStatusCode();
        System.out.println("The response status is " + statusCode);
        RestAssured.given().when().get(URL).then().assertThat().statusCode(200);
    }

    public static void getResponseBody() {
        System.out.println(RestAssured
                .given()
                .when()
                .get(URL)
                .then()
                .log()
                .body()
                .toString());
    }
}
