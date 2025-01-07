package api.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDetails {
    private String website;

    private int pages;

    private String subTitle;

    private String author;

    private String isbn;

    private String publisher;

    private String description;

    private String title;

    @JsonProperty("publish_date")
    private String publishDate;
}
