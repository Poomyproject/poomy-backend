package com.poomy.mainserver.user.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.poomy.mainserver.util.exception.ErrorCode;
import com.poomy.mainserver.util.exception.InvalidTokenException;
import com.poomy.mainserver.util.exception.common.CommonException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Collections;

@Component
public class GoogleService {

    @Value("${spring.google.client-id}")
    private String GOOGLE_CLIENT_ID;

    public String extractGoogleEmail(String idToken) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(GOOGLE_CLIENT_ID))
                .build();
        try{
            GoogleIdToken verifiedIdToken = verifier.verify(idToken);
            return verifiedIdToken.getPayload().getEmail();
        } catch(Exception e){
            throw new InvalidTokenException("Invalid Token");
        }
    }

}
