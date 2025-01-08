package org.example.gmailwithoauth.gmail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.util.Key;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomTokenResponse extends GoogleTokenResponse {

    @Key("expires_in")
    private Integer expiresIn;

}
