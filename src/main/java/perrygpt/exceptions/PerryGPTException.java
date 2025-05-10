package perrygpt.exceptions;

/**
 * The {@code PerryGPTException} class represents a custom exception used in the PerryGPT application.
 * It is thrown when an error specific to the application occurs.
 */
public class PerryGPTException extends Exception {

    /**
     * Constructs a {@code PerryGPTException} with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public PerryGPTException(String message) {
        super(message);
    }
}
