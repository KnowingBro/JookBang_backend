package com.knownbro.jookbang.domain.user.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.knownbro.jookbang.domain.user.domain.User;
import com.knownbro.jookbang.global.jwt.dto.TokenResponse;
import com.knownbro.jookbang.global.jwt.util.JwtProvider;
import com.knownbro.jookbang.global.oauth.OAuthAttributes;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class OAuth2GoogleService {

    private final String GOOGLE_TOKEN_REQUEST_URL = "https://oauth2.googleapis.com/token";
    private final String GOOGLE_USERINFO_REQUEST_URL = "https://www.googleapis.com/oauth2/v1/userinfo";
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;
    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String googleRedirectUri;

    private final OAuth2LoginService oAuth2LoginService;
    private final JwtProvider jwtProvider;

    public TokenResponse getJwtToken(String code) {
        String googleToken = getGoogleToken(code);
        OAuthAttributes oAuthAttributes = getOAuthAttributesByGoogleToken(googleToken);
        User user = oAuth2LoginService.saveOrUpdate(oAuthAttributes);
        return jwtProvider.generateToken(user.getEmail(), user.getAuthority().toString());
    }

    private String getGoogleToken(String code) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("code", code);
        params.add("client_id", googleClientId);
        params.add("client_secret", googleClientSecret);
        params.add("redirect_uri", googleRedirectUri);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity entity = new HttpEntity(params, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(GOOGLE_TOKEN_REQUEST_URL, HttpMethod.POST, entity, JsonNode.class);
        JsonNode accessToken = responseEntity.getBody();

        return accessToken.get("access_token").asText();
    }

    private OAuthAttributes getOAuthAttributesByGoogleToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer " + token);

        HttpEntity entity = new HttpEntity(headers);
        Map<String, Object> attributes = getJson(entity);

        return OAuthAttributes.create("google", attributes);
    }

    private Map<String, Object> getJson(HttpEntity entity) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(GOOGLE_USERINFO_REQUEST_URL, HttpMethod.GET, entity, String.class);
            String body = response.getBody();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(body);
            return (Map<String, Object>) jsonObject;
        } catch (ParseException e) {
            throw new IllegalArgumentException("인증 실패랄까?");
        }
    }
}