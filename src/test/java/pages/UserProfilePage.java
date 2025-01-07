package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class UserProfilePage {
    public final String profileEndpoint = "/profile";
    private final SelenideElement profileTable = $(".rt-tbody"),
            confirmationOkButton = $("#closeSmallModal-ok");
    private final String profileTableRow = ".rt-tr",
            deleteButton = "#delete-record-undefined";

    @Step("Открыть страницу профиля пользователя")
    public UserProfilePage openProfilePage() {
        open(profileEndpoint);
        return this;
    }

    @Step("Проверить, что в профиле пользователя находится книга с названием {title}")
    public UserProfilePage checkBookInProfileByTitle(String title) {
        profileTable.shouldHave(text(title));
        return this;
    }

    @Step("Удалить книгу с названием {title} из профиля пользователя")
    public UserProfilePage deleteBookInProfileByTitle(String title) {
        profileTable.$(byText(title)).closest(profileTableRow).$(deleteButton).click();
        confirmationOkButton.click();
        return this;
    }

    @Step("Проверить, что в профиле пользователя отсутствует книга с названием {title}")
    public UserProfilePage checkProfileHasNotBookByTitle(String title) {
        profileTable.shouldNotHave(text(title));
        return this;
    }
}
