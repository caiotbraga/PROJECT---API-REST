package med.voll.api;

import jakarta.validation.Valid;
import med.voll.api.domain.user.dto.UserAuthenticationData;
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

  @PostMapping
  public ResponseEntity userLogin(@RequestBody @Valid UserAuthenticationData userAuthenticationData){
    var token = new UsernamePasswordAuthenticationToken(userAuthenticationData.login(), userAuthenticationData.password());
    authenticationManager.authenticate(token);
    return ResponseEntity.ok().build();
  }
}
