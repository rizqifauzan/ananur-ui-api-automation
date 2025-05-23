package api.tests;

import api.automation.endpoints.UserApi;
import api.automation.utils.UserUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertTrue;

public class UserApiTest {

    @Test(groups = "api")
    public void testGetListUser() {
        int testPage = 2;
        File schema = new File(System.getProperty("user.dir") + "/src/test/resources/jsonSchema/GetListUsersSchema.json");
        assertTrue(schema.exists(), "JSON schema file not found!");

        Response response = UserApi.getListUser(testPage);
        response.then().log().all();

        UserUtils.verifyStatusCode(response, 200);
        UserUtils.verifyUsersPerPage(response, 6);
        UserUtils.verifyReturnedPage(response, testPage);
        UserUtils.verifyJsonSchema(response, schema);
    }

    @Test(groups = "api")
    public void testGetUserWithoutId() {
        File schema = new File(System.getProperty("user.dir") + "/src/test/resources/jsonSchema/GetListUsersSchema.json");
        assertTrue(schema.exists(), "JSON schema file not found!");

        Response response = UserApi.getListUser(1); // Default page
        response.then().log().all();

        UserUtils.verifyStatusCode(response, 200);
        UserUtils.verifyUsersPerPage(response, 6);
        UserUtils.verifyReturnedPage(response, 1);
        UserUtils.verifyJsonSchema(response, schema);

        // Extra validation langsung
        response.then().body("data.size()", equalTo(6));
        response.then().body("data[0].email", notNullValue());
    }

    @Test(groups = "api")
    public void testGetListUserWithInvalidPage() {
        int testPage = 99; // Edge case: kemungkinan tidak ada data
        Response response = UserApi.getListUser(testPage);
        response.then().log().all();

        UserUtils.verifyStatusCode(response, 200);
        UserUtils.verifyReturnedPage(response, testPage);
        UserUtils.verifyDataSize(response, 0);
    }
}
