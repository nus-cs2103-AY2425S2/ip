package tasker.command;

/**
 * The allowed enums for commands.
 */
public enum CommandType {
    DEADLINE,
    EVENT,
    TODO,
    MARK,
    UNMARK,
    LIST;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
