package exceptions;

/**
 * Concrete class that is thrown when trying to do something to an index that is out of bounds
 * of the current TaskList collection of tasks
 */
public class OutOfBoundsTaskException extends ThoughtBotException {
    /**
     * Constructor for OutOfBoundsTaskException class
     */
    public OutOfBoundsTaskException() {
        super("This task does not exist!");
    }
}
