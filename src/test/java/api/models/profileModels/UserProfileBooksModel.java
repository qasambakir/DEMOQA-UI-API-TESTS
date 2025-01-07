package api.models.profileModels;

import api.models.BookDetails;
import lombok.Data;

import java.util.List;

@Data
public class UserProfileBooksModel {

    private List<BookDetails> books;

    private String userId;

    private String username;

}
