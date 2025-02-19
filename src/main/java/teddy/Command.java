package teddy;

public enum Command{
    TODO,
    DEADLINE,
    EVENT,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    BYE,
    FIND;

    public static Command fromString(String command) throws TeddyException {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TeddyException("I don't understand the command: " + command);
        }
    }
}