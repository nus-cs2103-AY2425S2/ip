package Tom.commands;

/**
 * Represents the different types of commands that can be executed by the chatbot.
 */
public enum CommandType {
    TODO,DEADLINE,EVENT,LIST,MARK,UNMARK,DELETE,HELP,FIND,UNKNOWN,BYE;

    /**
     * Converts a user input string into a corresponding CommandType.
     *
     * @param command The user input string.
     * @return The corresponding CommandType, or UNKNOWN if the input does not match any known command.
     */
    public static CommandType fromString(String command) {
        switch (command.toLowerCase()) {
            case "todo" : return TODO;
            case "deadline" : return DEADLINE;
            case "event" : return EVENT;
            case "list" : return LIST;
            case "mark" : return MARK;
            case "unmark" : return UNMARK;
            case "delete" : return DELETE;
            case "help" : return HELP;
            case "find" : return FIND;
            case "bye" : return BYE;
            default: return UNKNOWN;
        }
    }
}
