package org.example.gmailwithoauth.gmail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * CustomTokenResponseDto is used in RestTemplate instead of GoogleTokenResponse;
 * GoogleTokenResponse throws JSON parse error: Can not set java.lang.Long field
 * To resolve this, making new DTO and processing it into GoogleTokenResponse
 */
@Slf4j
@Getter @Setter
public class CustomTokenResponseDto {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private Integer expiresIn;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("token_type")
    private String tokenType;

    public GoogleTokenResponse toGoogleTokenResponse() {
        GoogleTokenResponse googleTokenResponse = new GoogleTokenResponse();
        googleTokenResponse.setAccessToken(accessToken);
        googleTokenResponse.setExpiresInSeconds(expiresIn.longValue());
        googleTokenResponse.setScope(scope);
        googleTokenResponse.setTokenType(tokenType);

        return googleTokenResponse;
    }
}
