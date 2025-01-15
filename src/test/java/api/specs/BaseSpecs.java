package api.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;

public class BaseSpecs {

    // Спецификация без токена
    public static RequestSpecification requestWithoutToken = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build()
            .filter(withCustomTemplates());

    // Спецификация с токеном
    public static RequestSpecification requestWithToken(String token) {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token)
                .log(LogDetail.ALL)
                .build()
                .filter(withCustomTemplates());
    }
}
