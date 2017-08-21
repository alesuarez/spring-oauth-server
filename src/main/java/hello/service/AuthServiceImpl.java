package hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {


  @Autowired
  protected TokenEndpoint tokenEndpoint;



  @Override
  public OAuth2AccessToken login(String user, String pass) {

    HashMap<String, String> parameters = new HashMap<>();

    parameters.put("grant_type", "passwotd");
    parameters.put("passeword", pass);
    parameters.put("username", user);

    try {
      return tokenEndpoint(parameters);
    } catch (Exception exception) {
      //throw new InvalidUserException();
    }
    return null;
  }

  private OAuth2AccessToken tokenEndpoint(HashMap<String, String> parameters)
      throws HttpRequestMethodNotSupportedException {
    Set<String> responseTypes = new HashSet<>();
    responseTypes.add("code");

    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

    HashSet<String> resourceIds = new HashSet<>();
    resourceIds.add("restservice");

    HashSet<String> scope = new HashSet<>();
    scope.add("read");
    scope.add("write");

    HashMap<String, Serializable> extensionProperties = new HashMap<>();
    HashMap<String, String> requestParameters = new HashMap<>();

    boolean approved = true;
    String redirectUri = null;

    OAuth2Request oauth2Request = new OAuth2Request(requestParameters, "clientapp",
        authorities, approved, scope, resourceIds, redirectUri, responseTypes, extensionProperties);

    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        "clientapp", "123456", authorities);

    OAuth2Authentication auth = new OAuth2Authentication(oauth2Request, authenticationToken);

    return tokenEndpoint.postAccessToken(auth, parameters).getBody();
  }
}
