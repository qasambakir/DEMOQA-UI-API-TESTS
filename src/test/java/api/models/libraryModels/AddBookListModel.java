package api.models.libraryModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class AddBookListModel {

    private String userId;

    @JsonProperty("collectionOfIsbns") // Указываем, что поле в JSON называется "collectionOfIsbns"
    private List<IsbnItem> collectionOfIsbns;

}
