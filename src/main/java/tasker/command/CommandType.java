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

    /**
     * Lists out all available commands.
     *
     * @returns A list of commands each on a new line.
     */
    public static String listCommands() {
        CommandType[] commands = CommandType.values();
        int commandCount = commands.length;
        StringBuilder list = new StringBuilder("Valid commands:\n");

        for (int i = 0; i < commandCount; i++) {
            list.append(commands[i]);

            if (i != commandCount - 1) {
                list.append("\n");
            }
        }

        return list.toString();
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
