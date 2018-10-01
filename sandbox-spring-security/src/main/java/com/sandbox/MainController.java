package com.sandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

  @Autowired
  private OAuth2AuthorizedClientService authorizedClientService;

  @RequestMapping("/userinfo")
  public String userinfo(final OAuth2AuthenticationToken authentication) {
    // authentication.getAuthorizedClientRegistrationId() returns the
    // registrationId of the Client that was authorized during the Login flow
    OAuth2AuthorizedClient authorizedClient =
      this.authorizedClientService.loadAuthorizedClient(
        authentication.getAuthorizedClientRegistrationId(),
        authentication.getName());

    OAuth2AccessToken accessToken = authorizedClient.getAccessToken();

    return "userinfo";
  }
}