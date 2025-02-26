package Krypto.Exceptions;
/**
 * Represents a general exception in the Krypto application.
 * This class serves as the base class for all exceptions specific to Krypto.
 */
public class KryptoExceptions extends Exception {

    /**
     * Constructs a KryptoExceptions with the specified detail message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    private final String message;

    public KryptoExceptions(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
    @Override
    public String getMessage() {
        return this.toString();
    }
}