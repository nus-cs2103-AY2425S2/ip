package paimon.exceptions;

/**
 * Exception class for invalid description to paimon. 
 */
public class PaimonInvalidDescriptionException extends PaimonException {
    public PaimonInvalidDescriptionException(String message) {
        super("invlid description: " + message);
    }
}
