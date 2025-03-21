package Tom.commands;

import Tom.TomException;
import Tom.tasks.TaskType;
//BCD-Extension
/**
 * Parses user input into Commands.
 */
public class Parser {

    /**
     * Parses user input and returns the corresponding command.
     *
     * @param userInput The input entered by the user.
     * @return A Command object representing the user's request.
     * @throws TomException If the input command is not recognized.
     */
    public static Command parse(String userInput) throws TomException {
        String[] input = userInput.split(" ", 2);
        CommandType command = CommandType.fromString(input[0]);

        switch (command) {
            case LIST:
                return new ListCommand();
            case TODO:
                return new AddCommand(input, TaskType.TODO);
            case DEADLINE:
                return new AddCommand(input, TaskType.DEADLINE);
            case EVENT:
                return new AddCommand(input, TaskType.EVENT);
            case DELETE:
                return new DeleteCommand(input);
            case MARK:
                return new MarkCommand(input, true);
            case UNMARK:
                return new UnmarkCommand(input, false);
            case HELP:
                return new HelpCommand();
            case FIND:
                return new FindCommand(input);
            case BYE:
                return new ByeCommand();
            default:
                throw new TomException("Oops! I don't understand that command.");
        }
    }
}