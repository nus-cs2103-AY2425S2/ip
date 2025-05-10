package kyrie;

/**
 * Represents a parser to parse commands.
 */
public class Parser {
    /**
     * Parses the Bye command string and returns the corresponding command object.
     * 
     * @param commandString The command string to parse.
     * @return The corresponding command object.
     */
    public static ByeCommand parseByeCommand(String commandString) {
        return new ByeCommand();
    }

    /**
     * Parses the List command string and returns the corresponding command object.
     * 
     * @param commandString The command string to parse.
     * @return The corresponding command object.
     */
    public static ListCommand parseListCommand(String commandString) {
        return new ListCommand();
    }

    /**
     * Parses the Mark command string and returns the corresponding command object.
     * 
     * @param commandString The command string to parse.
     * @return The corresponding command object.
     * @throws KyrieException If the task number is invalid.
     */
    public static MarkCommand parseMarkCommand(String commandString) throws KyrieException {
        String[] commandParts = commandString.split(" ");
        if (commandParts.length != 2) {
            throw new KyrieException("Invalid command format, please follow the format: mark <task number>");
        }
        return new MarkCommand(Integer.parseInt(commandParts[1]));
    }

    /**
     * Parses the Unmark command string and returns the corresponding command object.
     * 
     * @param commandString The command string to parse.
     * @return The corresponding command object.
     * @throws KyrieException If the task number is invalid.
     */
    public static UnmarkCommand parseUnmarkCommand(String commandString) throws KyrieException {
        String[] commandParts = commandString.split(" ");
        if (commandParts.length != 2) {
            throw new KyrieException("Invalid command format, please follow the format: unmark <task number>");
        }
        return new UnmarkCommand(Integer.parseInt(commandParts[1]));
    }

    /**
     * Parses the Todo command string and returns the corresponding command object.
     * 
     * @param commandString The command string to parse.
     * @return The corresponding command object.
     * @throws KyrieException If the task description is invalid.
     */
    public static AddTodoCommand parseTodoCommand(String commandString) throws KyrieException {
        String[] commandParts = commandString.split(" ", 2);
        if (commandParts.length < 2) {
            throw new KyrieException("Invalid command format, please follow the format: todo <description>");
        }
        return new AddTodoCommand(commandParts[1]);
    }

    /**
     * Parses the Deadline command string and returns the corresponding command object.
     * 
     * @param commandString The command string to parse.
     * @return The corresponding command object.
     * @throws KyrieException If the task description is invalid.
     */
    public static AddDeadlineCommand parseDeadlineCommand(String commandString) throws KyrieException {
        String[] commandParts = commandString.split(" /by ");
        String[] subCommandParts = commandParts[0].split(" ", 2);
        if (commandParts.length != 2 || subCommandParts.length < 2) {
            throw new KyrieException("Invalid command format, please follow the format: deadline <description> /by <date time>");
        }
        return new AddDeadlineCommand(subCommandParts[1], new DateTime(commandParts[1]));
    }

    /**
     * Parses the Event command string and returns the corresponding command object.
     * 
     * @param commandString The command string to parse.
     * @return The corresponding command object.
     * @throws KyrieException If the task description is invalid.
     */
    public static AddEventCommand parseEventCommand(String commandString) throws KyrieException {
        String[] commandParts = commandString.split(" /from ");
        String[] subCommandParts = commandParts[0].split(" ", 2);
        String[] periodParts = commandParts[1].split(" /to ");
        if (commandParts.length != 2 || subCommandParts.length < 2 || periodParts.length != 2) {
            throw new KyrieException("Invalid command format, please follow the format: event <description> /from <start date time> /to <end date time>");
        }
        return new AddEventCommand(subCommandParts[1], new DateTime(periodParts[0]), new DateTime(periodParts[1]));
    }

    /**
     * Parses the Delete command string and returns the corresponding command object.
     * 
     * @param commandString The command string to parse.
     * @return The corresponding command object.
     * @throws KyrieException If the task number is invalid.
     */
    public static DeleteCommand parseDeleteCommand(String commandString) throws KyrieException {
        String[] commandParts = commandString.split(" ");
        if (commandParts.length != 2) {
            throw new KyrieException("Invalid command format, please follow the format: delete <task number>");
        }
        return new DeleteCommand(Integer.parseInt(commandParts[1]));
    }

    /**
     * Parses the Find command string and returns the corresponding command object.
     * 
     * @param commandString The command string to parse.
     * @return The corresponding command object.
     * @throws KyrieException If the search query is invalid.
     */
    public static FindCommand parseFindCommand(String commandString) throws KyrieException {
        String[] commandParts = commandString.split(" ");
        if (commandParts.length < 2) {
            throw new KyrieException("Invalid command format, please follow the format: find <keyword>");
        }
        return new FindCommand(commandString.substring(5));
    }

    public static TagCommand parseTagCommand(String commandString) throws KyrieException {
        String[] commandParts = commandString.split(" ");
        if (commandParts.length != 3) {
            throw new KyrieException("Invalid command format, please follow the format: tag <task number> <tag>");
        }
        return new TagCommand(Integer.parseInt(commandParts[1]), commandParts[2]);
    }

    /**
     * Parses the Invalid command string and returns the corresponding command object.
     * 
     * @param commandString The command string to parse.
     * @return The corresponding command object.
     * @throws KyrieException If the command string is invalid.
     */
    public static Command parseInvalidCommand(String commandString) throws KyrieException {
        throw new KyrieException("It seems you have entered an invalid command");
    }

    /**
     * Parses the command string and returns the corresponding command object.
     * 
     * @param commandString The command string to parse.
     * @return The corresponding command object.
     * @throws KyrieException If the command string is invalid.
     */
    public static Command parseCommand(String commandString) throws KyrieException {
        String[] commandParts = commandString.split(" ");
        String command = commandParts[0];
        switch (command) {
        case ByeCommand.COMMAND_WORD:
            return parseByeCommand(commandString);
        case ListCommand.COMMAND_WORD:
            return parseListCommand(commandString);
        case MarkCommand.COMMAND_WORD:
            return parseMarkCommand(commandString);
        case UnmarkCommand.COMMAND_WORD:
            return parseUnmarkCommand(commandString);
        case AddTodoCommand.COMMAND_WORD:
            return parseTodoCommand(commandString);
        case AddDeadlineCommand.COMMAND_WORD:
            return parseDeadlineCommand(commandString);
        case AddEventCommand.COMMAND_WORD:
            return parseEventCommand(commandString);
        case DeleteCommand.COMMAND_WORD:
            return parseDeleteCommand(commandString);
        case FindCommand.COMMAND_WORD:
            return parseFindCommand(commandString);
        case TagCommand.COMMAND_WORD:
            return parseTagCommand(commandString);
        default:
            return parseInvalidCommand(commandString);
        }
    }
}