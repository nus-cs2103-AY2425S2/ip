package prophet.exception;

/**
 * The ProphetException class is an exception type unique to Prophet.
 */
public class ProphetException extends Exception {
    protected String message;

    /**
     * Constructs a ProphetException.
     */
    public ProphetException() {
        this.message = "Oops! Hit a wall somewhere...\n";
    }

    /**
     * Returns a string representation of the exception.
     * @return the string that represents the Prophet's exception
     */
    public String toString() {
        return this.message;
    }
}
