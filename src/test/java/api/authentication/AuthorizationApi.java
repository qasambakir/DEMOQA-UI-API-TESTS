package api.authentication;

import io.restassured.http.ContentType;
import api.models.login.AuthenticationRequestModel;
import api.models.login.AuthenticationResponseModel;

import static io.restassured.RestAssured.given;

public class AuthorizationApi {

    public static AuthenticationResponseModel getAuthenticate() {
        String userName = System.getProperty("FirstLast");
        String password = System.getProperty("Qwerty789!");
        AuthenticationRequestModel login = new AuthenticationRequestModel(userName, password);
        return given()
                .body(login)
                .contentType(ContentType.JSON)
                .when()
                .post("/Account/v1/Login")
                .then()
                .statusCode(200)
                .extract().as(AuthenticationResponseModel.class);
    }
}