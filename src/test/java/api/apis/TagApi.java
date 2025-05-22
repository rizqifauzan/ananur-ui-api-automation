package api.apis;

import api.base.BaseApi;
import io.restassured.response.Response;

public class TagApi extends BaseApi {
    public Response getListTags() {
        return get("tag", null);
    }
}