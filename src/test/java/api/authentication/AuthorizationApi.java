package api.authentication;

import api.models.login.AuthenticationRequestModel;
import api.models.login.AuthenticationResponseModel;

import static api.specs.BaseSpecs.requestWithoutToken;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class AuthorizationApi {

    // Метод для получения `AuthenticationResponseModel`
    public static AuthenticationResponseModel getAuthenticate() {
                // Получение данных с заглушками по умолчанию
        String userName = System.getProperty("FirstLast", "FirstLast");
        String password = System.getProperty("Qwerty789!", "Qwerty789!");

        System.out.println("UserName: " + userName);
        System.out.println("Password: " + password);

        // Формирование тела запроса
        AuthenticationRequestModel loginRequest = new AuthenticationRequestModel(userName, password);

        // Отправка запроса
        return step("Отправить запрос логина и получить ответ", () ->
                given(requestWithoutToken)
                        .body(loginRequest)
                        .when()
                        .post("/Account/v1/Login")
                        .then()
                        .statusCode(200)
                        .extract().as(AuthenticationResponseModel.class)
        );
    }

    // Метод для получения токена
    public static String getToken() {
        AuthenticationResponseModel response = getAuthenticate();
        return response.getToken();
    }
}
