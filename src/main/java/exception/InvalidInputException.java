package exception;

/**
 * Exception for handling Invalid command inputs
 */
public class InvalidInputException extends Exception {
  public InvalidInputException(String msg) {
    super(msg);
  }
}
