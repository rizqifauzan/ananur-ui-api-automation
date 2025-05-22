package api.tests;

import api.apis.TagApi;
import api.apis.UserApi;
import com.github.javafaker.Faker;

public class BaseTest {
    protected UserApi userApi = new UserApi();
    protected TagApi tagApi = new TagApi();
    protected Faker faker = new Faker();
}
