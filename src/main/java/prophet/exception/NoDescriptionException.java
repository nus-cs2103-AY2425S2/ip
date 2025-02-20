package prophet.exception;

/**
 * The NoDescriptionException class is a custom exception that is
 * thrown when the user inputs a task without a valid description.
 */
public class NoDescriptionException extends ProphetException {
    protected String message;

    /**
     * Constructs a NoDescriptionException.
     */
    public NoDescriptionException() {
        this.message = "Part of your description is missing. Usage is as such: \n"
                + "todo description \nOR \ndeadline description /by when "
                + "\nOR \nevent description /from when /to when\n";
    }

    /**
     * Returns a string representation of the exception.
     * @return the string that represents the no description exception
     */
    public String toString() {
        return this.message;
    }
}
