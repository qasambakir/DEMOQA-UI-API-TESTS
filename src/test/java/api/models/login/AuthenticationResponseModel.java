package api.models.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationResponseModel {

    private String password;

    private String expires;

    @JsonProperty("created_date")
    private String createdDate;

    private boolean isActive;

    private String userId;

    private String userName;

    private String token;
}