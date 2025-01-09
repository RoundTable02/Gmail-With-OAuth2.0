package org.example.gmailwithoauth.dto;

import javax.annotation.Nullable;

/**
 * Record for Gmail Credential
 * Only used in GmailAPIProvider.refreshAccessToken()
 * @param client_id Client ID from Google Cloud Console
 * @param client_secret Client Secret from Google Cloud Console
 * @param refresh_token Refresh token (which is never expired) from Google Cloud Console
 * @param grant_type Value would be "refresh_token" when refreshing the token
 * @param access_token The token getting from Google API with this credential itself
 * @param userEmail Used for making Gmail Credential, Mail sender's email account
 */
public record GmailCredential(
        @Nullable
        String client_id,
        @Nullable
        String client_secret,
        @Nullable
        String refresh_token,
        @Nullable
        String grant_type,
        @Nullable
        String access_token,
        @Nullable
        String userEmail
) {

}
