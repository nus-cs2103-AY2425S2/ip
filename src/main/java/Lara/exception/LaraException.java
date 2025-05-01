/**
 * LaraException is a custom exception class used for handling errors
 * within the Lara chatbot program.
 *
 * Example usage:
 * throw new LaraException("Invalid command");
 *
 * @author Maliha Haque
 * @version 1.0
 */

package Lara.exception;

public class LaraException extends Exception{
    public LaraException(String message) {
        super(message);
    }
}
