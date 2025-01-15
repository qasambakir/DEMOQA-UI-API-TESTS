package helpers;

import api.models.login.AuthenticationResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static api.authentication.AuthorizationApi.getAuthenticate;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseLogin implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        // Получение токена и данных пользователя
        AuthenticationResponseModel loginModel = getAuthenticate();
        String token = loginModel.getToken();
        String userId = loginModel.getUserId();
        String expires = loginModel.getExpires();

        // Открываем любую страницу, чтобы получить доступ к cookies
        open("/images/Toolsqa.jpg");

        // Добавляем cookies в браузер
        getWebDriver().manage().addCookie(new Cookie("userID", userId));
        getWebDriver().manage().addCookie(new Cookie("expires", expires));
        getWebDriver().manage().addCookie(new Cookie("token", token));
    }
}
