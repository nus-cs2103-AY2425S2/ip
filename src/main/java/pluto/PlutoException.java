package pluto;

/**
 * Represents a custom exception specific to the Pluto chatbot
 * This exception is thrown when an exception occurs during
 * the execution of the chatbot, such as invalid commands
 * or incorrect format inputs
 */
public class PlutoException extends Exception {
    public PlutoException(String message) {
        super(message);
    }
}
