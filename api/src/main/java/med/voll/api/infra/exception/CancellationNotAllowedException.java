package med.voll.api.infra.exception;

public class CancellationNotAllowedException extends RuntimeException {

  public CancellationNotAllowedException (){
    super("Cancellation not allowed. Left less than 24 hours to consultation.");
  }
}
