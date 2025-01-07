package api.library;

import api.models.BookDetails;
import api.models.libraryModels.AddBookListModel;
import api.models.libraryModels.BookCollectionModel;
import api.models.libraryModels.BookItemModel;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;

import java.util.List;

import static api.specs.BaseSpecs.requestWithToken;
import static api.specs.BaseSpecs.requestWithoutToken;
import static io.restassured.RestAssured.given;

public class LibraryApi {

    @Step("Получить все доступные книги из библиотеки, отправив API запрос")
    public BookCollectionModel getAllBooks() {
        return given(requestWithoutToken)
                .when()
                .get("/Library/v1/Books")
                .then()
                .log().all()
                .extract().as(BookCollectionModel.class);
    }

    @Step("Выбрать случайную книгу из библиотеки")
    public BookDetails selectRandomBook() {
        BookCollectionModel bookCollection = getAllBooks();
        int bookIndex = new Faker().number().numberBetween(0, bookCollection.getBooks().size() - 1);
        return bookCollection.getBooks().get(bookIndex);
    }

    @Step("Добавить случайную книгу в профиль, отправив API запрос")
    public void addRandomBookToProfile(String userId, BookDetails book) {
        BookItemModel bookItemModel = new BookItemModel(book.getIsbn());
        List<BookItemModel> bookItemList = List.of(bookItemModel);
        AddBookListModel addBookListModel = new AddBookListModel(userId, bookItemList);

        given(requestWithToken)
                .body(addBookListModel)
                .when()
                .post("/Library/v1/Books")
                .then()
                .log().all()
                .statusCode(201);
    }
}