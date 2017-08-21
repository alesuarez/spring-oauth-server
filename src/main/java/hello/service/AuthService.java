package hello.service;

import org.springframework.security.oauth2.common.OAuth2AccessToken;

public interface AuthService {
  OAuth2AccessToken login(String user, String pass);
}
