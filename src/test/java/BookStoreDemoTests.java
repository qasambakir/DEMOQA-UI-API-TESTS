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

        userProfileApi.deleteAllBooksFromProfile(userId);
        BookDetails book = libraryApi.selectRandomBook();
        libraryApi.addRandomBookToProfile(userId, book);
        UserProfileApi.verifyBookIsbnInProfile(userId, book);

        UserProfilePage.openProfilePage()
                .checkBookInProfileByTitle(book.getTitle())
                .deleteBookInProfileByTitle(book.getTitle())
                .checkProfileHasNotBookByTitle(book.getTitle());
        UserProfileApi.verifyProfileIsEmpty(userId);
    }
}