package teddy;

public class Parser {
    public static String[] splitInput(String input) {
        assert input != null : "Input to splitInput() should not be null";
        return input.split(" ", 2);
    }

    public static Command parseCommand(String[] parts) throws TeddyException {
        return Command.fromString(parts[0]);
    }

}
