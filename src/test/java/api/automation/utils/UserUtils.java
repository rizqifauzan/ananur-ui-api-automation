package api.automation.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;

public class UserUtils {

    private static final String BASE_URL = "https://reqres.in";

    // Contoh API key yang diambil dari environment variable
    private static final String API_KEY = System.getenv("REQRES_API_KEY");

    public static Response getListUsers(int page) {
        if (API_KEY == null || API_KEY.isEmpty()) {
            // Request tanpa API key, kemungkinan akan dapat 401
            return RestAssured
                    .given()
                    .baseUri(BASE_URL)
                    .accept("application/json")
                    .queryParam("page", page)
                    .when()
                    .get("/api/users");
        } else {
            // Request dengan API key di header
            return RestAssured
                    .given()
                    .baseUri(BASE_URL)
                    .header("x-api-key", API_KEY)
                    .accept("application/json")
                    .queryParam("page", page)
                    .when()
                    .get("/api/users");
        }
    }

    public static void verifyStatusCode(Response response, int expectedStatusCode) {
        response.then().assertThat().statusCode(expectedStatusCode);
        System.out.println("✅ Status code is " + expectedStatusCode);
    }

    public static void verifyReturnedPage(Response response, int page) {
        response.then().assertThat().body("page", equalTo(page));
        System.out.println("✅ Returned page is " + page);
    }

    public static void verifyUsersPerPage(Response response, int expectedPerPage) {
        response.then().assertThat().body("per_page", equalTo(expectedPerPage));
        System.out.println("✅ Users per page is " + expectedPerPage);
    }

    public static void verifyJsonSchema(Response response, File schemaFile) {
        if (schemaFile == null || !schemaFile.exists()) {
            throw new IllegalArgumentException("❌ JSON schema file is missing or invalid.");
        }
        response.then().assertThat().body(io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema(schemaFile));
        System.out.println("✅ JSON schema validated.");
    }

    public static void verifyDataSize(Response response, int expectedSize) {
        response.then().assertThat().body("data.size()", equalTo(expectedSize));
        System.out.println("✅ Data size is " + expectedSize);
    }
}
