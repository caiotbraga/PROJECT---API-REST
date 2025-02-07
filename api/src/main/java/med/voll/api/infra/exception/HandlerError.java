package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class HandlerError {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity handlerError404(){
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity handlerError400(MethodArgumentNotValidException ex){
    var errors = ex.getFieldErrors();
    return ResponseEntity.badRequest().body(errors.stream().map(ErrorValidator::new).toList());
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity tratarErroBadCredentials() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity tratarErroAuthentication() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity tratarErroAcessoNegado() {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity tratarErro500(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " +ex.getLocalizedMessage());
  }

  private record ErrorValidator(String field, String message){

    public ErrorValidator(FieldError error){
      this(error.getField(), error.getDefaultMessage());
    }
  }

}
