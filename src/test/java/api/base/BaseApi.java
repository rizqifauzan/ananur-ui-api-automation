package api.base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static config.EnvConfig.getEnv;

public class BaseApi {

    private static final String BASE_URL = "https://dummyapi.io/data/v1/";

    protected RequestSpecification requestSpec() {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .header("app-id", getEnv("SECRET_KEY"))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all();
    }

    protected Response get(String endpoint, Map<String, String> queryParams) {
        RequestSpecification requestSpec = requestSpec();
        if (queryParams != null) {
            requestSpec.queryParams(queryParams);
        }
        return requestSpec.get(endpoint);
    }

    protected Response post(String endpoint, Object body) {
        return requestSpec().body(body).post(endpoint);
    }

    protected Response put(String endpoint, Object body) {
        return requestSpec()
                .body(body)
                .put(endpoint);
    }

    protected Response delete(String endpoint) {
        return requestSpec().delete(endpoint);
    }
}