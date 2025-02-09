package med.voll.api.infra.exception;

public class ValidateDataException extends RuntimeException {

  public ValidateDataException(String mensagem) {
    super(mensagem);
  }
}
