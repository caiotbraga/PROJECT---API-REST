package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.user.User;
import med.voll.api.domain.user.dto.UserAuthenticationData;
import med.voll.api.infra.security.TokenResponse;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping
  public ResponseEntity userLogin(@RequestBody @Valid UserAuthenticationData userAuthenticationData){
    var authenticationToken = new UsernamePasswordAuthenticationToken(userAuthenticationData.login(), userAuthenticationData.password());
    var authentication = authenticationManager.authenticate(authenticationToken);

    var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
    return ResponseEntity.ok(new TokenResponse(tokenJWT));
  }
}
