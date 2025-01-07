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
    public void beforeEach(ExtensionContext context){
        AuthenticationResponseModel loginModel = getAuthenticate();
        String token = loginModel.getToken(),
                userId = loginModel.getUserId(),
                expires = loginModel.getExpires();

        open("/images/Toolsqa.jpg");
        getWebDriver().manage().addCookie(new Cookie("userID", userId));
        getWebDriver().manage().addCookie(new Cookie("expires", expires));
        getWebDriver().manage().addCookie(new Cookie("token", token));
    }
}
