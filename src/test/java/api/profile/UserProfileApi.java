package api.profile;

import api.authentication.AuthorizationApi;
import api.models.BookDetails;
import api.models.profileModels.UserProfileBooksModel;
import io.qameta.allure.Step;

import static api.specs.BaseSpecs.requestWithToken;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class UserProfileApi {

    @Step("Удалить все книги из профиля пользователя с ID {userId}")
    public static void deleteAllBooksFromProfile(String userId) {
        given(requestWithToken(AuthorizationApi.getToken()))
                .queryParam("UserId", userId)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .log().ifError()
                .statusCode(204);
    }

    @Step("Получить список всех книг в профиле пользователя с ID {userId}")
    public static UserProfileBooksModel getAllBooksFromProfile(String userId) {
        return given(requestWithToken(AuthorizationApi.getToken()))
                .pathParam("userId", userId)
                .when()
                .get("/Account/v1/User/{userId}")
                .then()
                .log().ifError()
                .statusCode(200)
                .extract()
                .as(UserProfileBooksModel.class);
    }

    @Step("Проверить, что ISBN книги в профиле пользователя с ID {userId} совпадает с ISBN книги из библиотеки")
    public static void verifyBookIsbnInProfile(String userId, BookDetails book) {
        UserProfileBooksModel addedBookInProfile = getAllBooksFromProfile(userId);
        assertThat(addedBookInProfile.getBooks())
                .withFailMessage("Книга не найдена в профиле!")
                .anyMatch(b -> b.getIsbn().equals(book.getIsbn()));
    }

    @Step("Проверить, что профиль пользователя с ID {userId} не содержит книг")
    public static void verifyProfileIsEmpty(String userId) {
        UserProfileBooksModel allBooksInProfile = getAllBooksFromProfile(userId);
        assertThat(allBooksInProfile.getBooks())
                .withFailMessage("Профиль не пуст!")
                .isEmpty();
    }
}
