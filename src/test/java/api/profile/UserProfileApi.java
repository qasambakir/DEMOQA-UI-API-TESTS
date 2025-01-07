package api.profile;

import api.models.BookDetails;
import api.models.profileModels.UserProfileBooksModel;
import io.qameta.allure.Step;

import static api.specs.BaseSpecs.requestWithToken;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserProfileApi {

    @Step("Удалить все книги из профиля пользователя с ID {userId}")
    public void deleteAllBooksFromProfile(String userId) {
        given(requestWithToken)
                .queryParam("UserId", userId)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .log().all()
                .statusCode(204);
    }

    @Step("Получить список всех книг в профиле пользователя с ID {userId}")
    public static UserProfileBooksModel getAllBooksFromProfile(String userId) {
        return given(requestWithToken)
                .pathParam("userId", userId)
                .when()
                .get("/Account/v1/User/{userId}")
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(UserProfileBooksModel.class);
    }

    @Step("Проверить, что ISBN книги в профиле пользователя с ID {userId} совпадает с ISBN книги из библиотеки")
    public static void verifyBookIsbnInProfile(String userId, BookDetails book) {
        UserProfileBooksModel addedBookInProfile = getAllBooksFromProfile(userId);
        assertThat(addedBookInProfile.getBooks().get(0).getIsbn()).isEqualTo(book.getIsbn());
    }

    @Step("Проверить, что профиль пользователя с ID {userId} не содержит книг")
    public static void verifyProfileIsEmpty(String userId) {
        UserProfileBooksModel allBooksInProfile = getAllBooksFromProfile(userId);
        assertThat(allBooksInProfile.getBooks().size()).isEqualTo(0);
    }
}
