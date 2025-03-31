package clovis;

/**
 * The {@code ClovisException} class represents custom exceptions specific to the Clovis application.
 */
public class ClovisException extends Exception {
    /**
     * Constructs a new {@code ClovisException} with the specified detail message.
     *
     * @param message the detail message describing the exception.
     */
    public ClovisException(String message) {
        super(message);
    }
}
