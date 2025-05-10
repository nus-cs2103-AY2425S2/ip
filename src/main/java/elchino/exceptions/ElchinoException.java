package elchino.exceptions;

import java.lang.Exception;

/**
 * Represents an exception specific to Elchino.
 */
public class ElchinoException extends Exception {
    /**
     * Constructor for ElchinoException with a specific error message.
     * @param message The message to be displayed when the exception is thrown.
     */
    public ElchinoException(String message) {
        super(message);
    }
}
