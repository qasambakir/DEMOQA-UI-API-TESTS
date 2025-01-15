package api.models.libraryModels;


import api.models.BookDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BookCollectionModel {

    @JsonProperty("books")
    private List<BookDetails> books;

}
