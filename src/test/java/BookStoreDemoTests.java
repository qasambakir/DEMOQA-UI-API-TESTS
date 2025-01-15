import api.models.BookDetails;
import api.profile.UserProfileApi;
import helpers.WithAuthentication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UI-API-TEST")
public class BookStoreDemoTests extends TestBase {

    @DisplayName("UI удаление книги из профиля")
    @WithAuthentication
    @Test
    public void deleteBookTest() {
        // Удаляем все книги из профиля
        UserProfileApi.deleteAllBooksFromProfile(userId);

        // Получаем все книги из профиля
        userProfileApi.getAllBooksFromProfile(userId);

        // Выбираем случайную книгу из библиотеки
        BookDetails book = libraryApi.selectRandomBook();

        // Добавляем книгу в профиль
        libraryApi.addRandomBookToProfile(userId, book);

        // Проверяем, что книга добавлена
        UserProfileApi.verifyBookIsbnInProfile(userId, book);

        // Проверяем на UI
        UserProfilePage.openProfilePage()
                .checkBookInProfileByTitle(book.getTitle())
                .deleteBookInProfileByTitle(book.getTitle())
                .checkProfileHasNotBookByTitle(book.getTitle());

        // Проверяем, что профиль пуст
        UserProfileApi.verifyProfileIsEmpty(userId);
    }
}