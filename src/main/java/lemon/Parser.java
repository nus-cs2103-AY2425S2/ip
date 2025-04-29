/**
 * Represents a parser that can parse the user commands
 * It extracts the first word from the user command,
 * identity the type of command and create a Command object
 */

package lemon;

public class Parser {
    public static Command parse(String userInput) {
        String[] parts = userInput.split(" ", 2);
        String command = parts[0];

        switch (command) {
        case "list":
            return new Command(CommandType.LIST);
        case "delete":
            return new Command(CommandType.DELETE);
        case "mark":
            return new Command(CommandType.MARK);
        case "unmark":
            return new Command(CommandType.UNMARK);
        case "bye":
            return new Command(CommandType.EXIT);
        case "help":
            return new Command(CommandType.HELP);
        case "technical":
            return new Command(CommandType.HELP_TECH);
        case "emotional":
            return new Command(CommandType.HELP_EMO);
        case "todo":
        case "deadline":
        case "event":
            return new Command(CommandType.ADD);
        default:
            return new Command(CommandType.UNKNOWN);
        }

    }
}