package api.automation.endpoints;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi {

    private static final String BASE_URL = "https://reqres.in/api";

    // API key bisa kamu simpan di environment variable
    private static final String API_KEY = System.getenv("REQRES_API_KEY");

    public static Response getListUser(int page) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("x-api-key", API_KEY != null ? API_KEY : "reqres-free-v1") // header API key wajib
                .queryParam("page", page)
                .log().all()
                .when()
                .get("/users")
                .then()
                .log().all()
                .extract().response();
    }
}
