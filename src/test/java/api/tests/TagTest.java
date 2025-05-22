package api.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

@Test(groups = {"api"})
public class TagTest extends BaseTest {
    @Test(description = "Test Get Tags")
    public void testGetAllTags() {
        Response res = tagApi.getListTags();
        res.then().assertThat().statusCode(200)
                .time(lessThan(2000L));
    }
}