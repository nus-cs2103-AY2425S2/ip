package phil.exception;

/**
 * Represents the Command Types, to be used with phil.exception.InvalidArgumentException.
 */
public enum CommandType {
    CREATE_TODO,
    CREATE_DEADLINE,
    CREATE_EVENT,
    MARK_DONE,
    MARK_UNDONE,
    DELETE_TASK,
    FIND_TASK,
    CREATE_NOTE,
    DELETE_NOTE
}
