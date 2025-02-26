package Krypto.Exceptions;

/**
 * Represents an exception thrown when a ToDo task is used incorrectly, such as when it carries time information.
 */
public class ToDoException extends KryptoExceptions {

    /**
     * Constructs a ToDoException with no detail message.
     */
    public ToDoException() {
        super("");
    }

    @Override
    public String toString() {
        return "ToDo tasks don't carry time information.\nUse format todo <task description>";
    }
}