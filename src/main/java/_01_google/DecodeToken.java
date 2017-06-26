package _01_google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;

import _00_bean.Global;

public class DecodeToken {

	public static GoogleIdToken.Payload getPayload(String tokenString) throws Exception {

		JacksonFactory jacksonFactory = new JacksonFactory();
		GoogleIdTokenVerifier googleIdTokenVerifier = new GoogleIdTokenVerifier(
														new NetHttpTransport(), jacksonFactory);

		GoogleIdToken token = GoogleIdToken.parse(jacksonFactory, tokenString);

		if (googleIdTokenVerifier.verify(token)) {
			GoogleIdToken.Payload payload = token.getPayload();
			if (!(Global.getGoogleClientId()).equals(payload.getAudience())) {
				throw new IllegalArgumentException("Audience mismatch");
			} else if (!(Global.getGoogleClientId()).equals(payload.getAuthorizedParty())) {
				throw new IllegalArgumentException("Client ID mismatch");
			}
			return payload;
		} else {
			throw new IllegalArgumentException("id token cannot be verified");
		}
	}
}