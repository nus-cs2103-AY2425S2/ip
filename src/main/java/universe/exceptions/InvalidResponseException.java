package universe.exceptions;

/**
 * Represents an exception that is thrown when user inputs an invalid response.
 *
 * @author yuqing-tham
 */
public class InvalidResponseException extends UniverseException {
    /**
     * Constructs a new InvalidResponseException with the specified detail message.
     */
    public InvalidResponseException() {
        super("Sorry, this word doesn't exist in the Universe library yet.\n"
                + "Check back again in a million light years or reenter your command.");
    }
}
