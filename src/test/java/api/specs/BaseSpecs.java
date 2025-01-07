package api.specs;

import api.authentication.AuthorizationApi;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class BaseSpecs {
    public static RequestSpecification requestWithToken = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .addHeader("Authorization", "Bearer " + AuthorizationApi.getAuthenticate().getToken())
            .log(LogDetail.ALL)
            .build()
            .filter(withCustomTemplates());
    public static RequestSpecification requestWithoutToken = with()
            .filter(withCustomTemplates())
            .log().all();
}
