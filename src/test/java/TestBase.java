import api.library.LibraryApi;
import api.models.login.AuthenticationResponseModel;
import api.profile.UserProfileApi;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.UserProfilePage;

import java.util.Map;

import static api.authentication.AuthorizationApi.getAuthenticate;

public class TestBase {
    private static AuthenticationResponseModel loginModel;
    protected String token = loginModel.getToken();
    protected String userId = loginModel.getUserId();
    protected String expires = loginModel.getExpires();

     UserProfileApi userProfileApi = new UserProfileApi();
     LibraryApi libraryApi = new LibraryApi();
     UserProfilePage UserProfilePage = new UserProfilePage();

    @BeforeAll
    public static void setUp() {
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.baseURI = "https://demoqa.com";
        loginModel = getAuthenticate();

        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("version");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true,
                "videoName", System.getProperty("REMOTE_VIDEO_URL", "default_video.mp4")
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void afterEach() {
        Attach.screenshotAs("Last screenshot");
        Attach.addVideo();
        Attach.pageSource();
        Selenide.closeWebDriver();
    }
}
