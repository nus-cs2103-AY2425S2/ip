package laffy.command;

/**
 * Represents the command words that can be used in the application.
 */
public enum CommandWord {
    EXIT("bye"),

    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),

    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),

    DELETE("delete"),
    FIND("find"),
    UPCOMING("soon"),

    INVALID("");

    private final String commandWord;

    /**
     * Constructor for CommandWord.
     *
     * @param commandWord The command word.
     */
    CommandWord(String commandWord) {
        this.commandWord = commandWord;
    }

    /**
     * Returns the command word.
     *
     * @return The command word.
     */
    @Override
    public String toString() {
        return commandWord;
    }

    /**
     * Returns the CommandWord enum corresponding to the given command word.
     *
     * @param commandWord The command word.
     * @return The CommandWord enum corresponding to the given command word.
     */
    public static CommandWord fromString(String commandWord) {
        for (CommandWord c : CommandWord.values()) {
            if (c.commandWord.equals(commandWord)) {
                return c;
            }
        }
        return INVALID;
    }
}
