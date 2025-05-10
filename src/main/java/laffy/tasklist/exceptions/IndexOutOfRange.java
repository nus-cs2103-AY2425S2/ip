package laffy.tasklist.exceptions;

/**
 * Represents an exception where an index is out of range.
 * Occurs while executing a command.
 */
public class IndexOutOfRange extends TaskListException {
    private static final String message = "Index out of range. ";

    public IndexOutOfRange(String explanation) {
        super(message + explanation);
    }
}
