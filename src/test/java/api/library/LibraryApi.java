package api.library;

import api.authentication.AuthorizationApi;
import api.models.BookDetails;
import api.models.libraryModels.BookCollectionModel;
import api.models.libraryModels.IsbnItem;
import api.models.libraryModels.AddBookListModel;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;

import java.util.List;

import static api.specs.BaseSpecs.requestWithToken;
import static api.specs.BaseSpecs.requestWithoutToken;
import static io.restassured.RestAssured.given;

public class LibraryApi {

    @Step("Получить все доступные книги из каталога, отправив API запрос")
    public BookCollectionModel getAllBooksInStore() {
        return given(requestWithoutToken)
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .log().all()
                .extract().as(BookCollectionModel.class);
    }
    @Step("Выбрать случайную книгу из каталога")
    public BookDetails selectRandomBook(){
        BookCollectionModel arrayOfBooksStore = getAllBooksInStore();
        int bookIndex = new Faker().number().numberBetween(0, arrayOfBooksStore.getBooks().size() - 1);
        return arrayOfBooksStore.getBooks().get(bookIndex);
    }
    @Step("Добавить случайную книгу в профиль, отправив API запрос")
    public void addRandomBookToProfile(String userId, BookDetails book){
        IsbnItem isbnItem = new IsbnItem(book.getIsbn());
        List<IsbnItem> isbnItemList  = List.of(isbnItem);
        AddBookListModel addBookListModel = new AddBookListModel(userId, isbnItemList);

        given(requestWithToken(AuthorizationApi.getToken()))
                .body(addBookListModel)
                .log().all()
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .log().all()
                .statusCode(201);
    }
}