package tooth.exception;

/**
 * Exception when trying to load from an empty TaskList
 */
public class MemoryIsEmptyException extends ToothException {
    public MemoryIsEmptyException(String message) {
        super(message);
    }
}
