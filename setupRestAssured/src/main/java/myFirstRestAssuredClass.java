import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class myFirstRestAssuredClass {
    final static String URL = "https://api.publicapis.org/";

    public static void main(String[] args) {
        RestAssured.baseURI = URL;

        getResponseStatus();

        getResponseHeaders();

        getResponseHeader();

        Response responseBody = getResponseBodyAll();
        System.out.println(responseBody.body().prettyPrint());

        Response requestWithParams = getRequestWithParams();
        requestWithParams.body().prettyPrint();

        Response responseBodyJSON = getResponseBodyJSON();
        //responseBodyJSON.body().print();
        responseBodyJSON.body().prettyPrint();
    }

    private static Response getResponseBodyJSON() {
        return RestAssured
                .given()
                .header("Content-type", "application/json")
                .headers("Server", "Tomcat")
                .when()
                .get("categories");
    }

    private static Response getRequestWithParams() {
        return RestAssured.given()
                .with()
                .queryParam("title", "cat")
                .queryParam("description", "Rwanda")
                //.queryParam("auth", "apiKey")
                //.queryParam("https", "true")
                .when()
                .get("entries");
    }

    private static void getResponseHeader() {
        System.out.println(RestAssured
                .given()
                .when()
                .get(URL)
                .getHeader("Content-Type"));
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

    public static Response getResponseBodyAll() {
        return RestAssured
                .given()
                .when()
                .get("entries");
    }
}
