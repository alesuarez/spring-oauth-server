package hello.controller;

import hello.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  @Autowired
  private AuthService authService;

  @RequestMapping("/auth/login")
  public String login() {
    OAuth2AccessToken oauth2AccessToken = authService.login("roy","spring");
    return oauth2AccessToken.getValue();
  }
}
