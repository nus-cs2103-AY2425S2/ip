package enums;

/**
 * Enum of all possible commands
 */
public enum CommandTypes {
    TODO,
    DEADLINE,
    EVENT,
    MARK,
    UNMARK,
    LIST,
    LS,
    BYE,
    Q,
    DELETE,
    DEL,
    INVALID,
    FIND,
    SORT;

    /**
     * Static method to parse command from String to the respective enum
     *
     * @param command raw String input of the command
     * @return String formatted to uppercase or Invalid if not found in enum
     */
    public static CommandTypes fromString(String command) {
        try {
            return CommandTypes.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandTypes.INVALID;
        }
    }
}