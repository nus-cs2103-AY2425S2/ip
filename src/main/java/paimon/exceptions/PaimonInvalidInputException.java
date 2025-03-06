package paimon.exceptions;

/**
 * Exception class for invalid input to paimon. 
 */
public class PaimonInvalidInputException extends PaimonException {
    public PaimonInvalidInputException(String message) {
        super("invalid input: " + message);
    }
}
