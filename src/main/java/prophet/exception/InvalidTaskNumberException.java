package prophet.exception;

/**
 * The InvalidTaskNumberException class is a custom exception that is
 * thrown when the user inputs an invalid task number (ie. a non-integer).
 */
public class InvalidTaskNumberException extends ProphetException {
    protected String message;

    /**
     * Constructs an InvalidTaskNumberException.
     */
    public InvalidTaskNumberException() {
        this.message = "Whoops! Invalid number. Please key in a valid integer\n";
    }

    /**
     * Returns a string representation of the exception.
     * @return the string that represents the invalid number exception
     */
    public String toString() {
        return this.message;
    }
}
