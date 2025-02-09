package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
  public ResponseEntity handleError404(){
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity handleError400(MethodArgumentNotValidException ex){
    var errors = ex.getFieldErrors();
    return ResponseEntity.badRequest().body(errors.stream().map(ErrorValidator::new).toList());
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity handleErroBadCredentials() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Credenciais inválidas"));
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity handleErroAuthentication() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Falha na autenticação"));
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity handleErrorAccessDenied() {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("Acesso negado"));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity handleErro500(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(ex.getLocalizedMessage()));
  }

  @ExceptionHandler(CancellationNotAllowedException.class)
  public ResponseEntity handleCancellationNotAllowed(Exception ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(ex.getLocalizedMessage()));
  }

  @ExceptionHandler(ValidateDataException.class)
  public ResponseEntity handleValidateData(Exception ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getLocalizedMessage()));
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ex.getMessage()));
  }

  private record ErrorValidator(String field, String message){

    public ErrorValidator(FieldError error){
      this(error.getField(), error.getDefaultMessage());
    }
  }

  private static class ErrorResponse {
    private String error;

    public ErrorResponse(String message) {
      this.error = message;
    }

    public String getMessage() {
      return error;
    }

    public void setMessage(String message) {
      this.error = message;
    }
  }

}
