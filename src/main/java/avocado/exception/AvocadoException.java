package avocado.exception;

/**
 * Represents exceptions specific to Avocado.
 */
public class AvocadoException extends Exception {
    /**
     * Constructs an AvocadoException with the specified detail message.
     *
     * @param message The detail message.
     */
    public AvocadoException(String message) {
        super(message);
    }
}
