package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

  private record ErrorValidator(String field, String message){

    public ErrorValidator(FieldError error){
      this(error.getField(), error.getDefaultMessage());
    }
  }

}
