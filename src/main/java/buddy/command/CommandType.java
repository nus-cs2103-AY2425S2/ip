package buddy.command;

/**
 * Represents possible command types for Buddy chatbot
 */
public enum CommandType {
    TODO("todo TASK_DESCRIPTION"),
    DEADLINE("deadline TASK_DESCRIPTION /by yyyy-MM-dd HHmm"),
    EVENT("event TASK_DESCRIPTION /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm"),
    BYE("bye"),
    LIST("list"),
    MARK("mark INDEX"),
    UNMARK("unmark INDEX"),
    DELETE("delete INDEX"),
    FIND("find KEYWORD"),
    UPDATE("update INDEX /FIELD_TO_UPDATE NEW_INFO"),
    HELP("help");

    private final String representation;

    CommandType(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
