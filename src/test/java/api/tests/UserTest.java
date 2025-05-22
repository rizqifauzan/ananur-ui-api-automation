package api.tests;

import api.models.User.UserFull;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;

@Test(groups = {"api"})
public class UserTest extends BaseTest{
    private String idUser = null;
    private UserFull userFull = null;

    @Test(description = "Test Get Users", groups = {"api"}, priority = 1)
    public void testGetAllUser() {
        Response res = userApi.getListUsers();
        res.then().assertThat().statusCode(200)
                .time(lessThan(2000L))
                .assertThat().body("data.firstName", hasItems("Carolina"));

        String jsonResponse = res.getBody().asString();
        idUser = JsonPath.read(jsonResponse, "$.data[0].id");
        System.out.println(idUser);
    }

    @Test(description = "Test Get 10 Users", groups = {"api"})
    public void testGetLimitedUser() {
        Map<String, String> queryParams = Map.of("limit", "10");
        Response res = userApi.getListUsers(queryParams);
        res.then().assertThat().statusCode(200)
                .time(lessThan(2000L));

        String jsonResponse = res.getBody().asString();
        var data = JsonPath.read(jsonResponse, "$.data.length()");
        Assert.assertEquals(data, 10);
    }

    @Test(description = "Test Get User By Id", groups = {"api"}, dependsOnMethods = {"testGetAllUser"})
    public void testGetUserById() {
        Response res = userApi.getUserById(idUser);
        res.then().assertThat().statusCode(200)
                .time(lessThan(2000L));

        String jsonResponse = res.getBody().asString();
        Assert.assertEquals(JsonPath.read(jsonResponse, "$.id"), idUser);
    }

    @Test(description = "Test Create User", groups = {"api"})
    public void testCreateUser() {
        userFull = new UserFull(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress());
        Response res = userApi.createUser(userFull);
        System.out.println(res.getBody().asString());
        res.then().assertThat().statusCode(200)
                .time(lessThan(2000L))
                .assertThat().body("id", notNullValue())
                .assertThat().body("firstName", equalTo(userFull.getFirstName()))
                .assertThat().body("lastName", equalTo(userFull.getLastName()))
                .assertThat().body("email", equalTo(userFull.getEmail()));

        String jsonResponse = res.getBody().asString();
        userFull.setId(JsonPath.read(jsonResponse, "$.id"));
        userFull.setRegisterDate(JsonPath.read(jsonResponse, "$.registerDate"));
    }

    @Test(description = "Test Update Data of Created User", groups = {"api"})
    public void testUpdateUser() {
        userFull.setFirstName(faker.name().firstName());
        userFull.setLastName(faker.name().lastName());
        userFull.setPhone(faker.phoneNumber().cellPhone());
        Response res = userApi.updateUser(userFull.getId(), userFull);
        System.out.println(res.getBody().asString());
        res.then().assertThat().statusCode(200)
                .time(lessThan(2000L))
                .assertThat().body("id", notNullValue())
                .assertThat().body("firstName", equalTo(userFull.getFirstName()))
                .assertThat().body("lastName", equalTo(userFull.getLastName()))
                .assertThat().body("email", equalTo(userFull.getEmail()))
                .assertThat().body("gender", notNullValue())
                .assertThat().body("phone", notNullValue());
    }

    @Test(description = "Test Delete Created User", groups = {"api"}, dependsOnMethods = {"testUpdateUser"})
    public void testDeleteUser() {
        Response res = userApi.deleteUser(userFull.getId());
        System.out.println(res.getBody().asString());
        res.then().assertThat().statusCode(200)
                .time(lessThan(2000L));
    }

    @Test(description = "Test Double Delete Created User", groups = {"api"}, dependsOnMethods = {"testDeleteUser"})
    public void testDoubleDeleteUser() {
        // 1. create new user
        userFull = new UserFull(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress());
        Response userRes = userApi.createUser(userFull);
        userFull.setId(JsonPath.read(userRes.getBody().asString(), "$.id"));

        // 2. delete the created user
        Response deleteRes = userApi.deleteUser(userFull.getId());
        deleteRes.then().assertThat().statusCode(200)
                .time(lessThan(2000L));

        // 3. delete it again
        Response doubleDeleteRes = userApi.deleteUser(userFull.getId());
        System.out.println(doubleDeleteRes.getBody().asString());
        doubleDeleteRes.then().assertThat().statusCode(404).assertThat().body("error", equalTo("RESOURCE_NOT_FOUND"));
    }
}