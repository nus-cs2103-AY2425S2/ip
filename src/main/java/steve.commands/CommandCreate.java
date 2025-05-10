package steve.commands;

import steve.exceptions.InvalidCommandException;
import steve.tasks.TaskManager;

/**
 * Creates different Command Objects based on user input.
 * This class takes user input, processes it, and returns a corresponding command object.
 * The commands can be related to tasks, such as find, list, mark, unmark, etc.
 */
public class CommandCreate {

    /**
     * Creates the appropriate command based on the given user input.
     *
     * @param userInput The input string from the user, representing a command.
     * @param taskManager The TaskManager instance to manage tasks related to the command.
     * @return The corresponding Command object.
     * @throws InvalidCommandException If the command is unknown or invalid.
     */
    public static Command createCommand(String userInput, TaskManager taskManager) throws InvalidCommandException {
        String firstWord = userInput.split(" ")[0];
        switch (firstWord) {
        case "find":
            return new FindCommand(taskManager, userInput);
        case "list":
            return new ListCommand(taskManager);
        case "mark":
            return new MarkCommand(taskManager, userInput);
        case "unmark":
            return new UnmarkCommand(taskManager, userInput);
        case "todo":
            return new TodoCommand(taskManager, userInput);
        case "deadline":
            return new DeadlineCommand(taskManager, userInput);
        case "event":
            return new EventCommand(taskManager, userInput);
        case "delete":
            return new DeleteCommand(taskManager, userInput);
        case "contact":
            return new ContactCommand(taskManager, userInput);
        case "clients":
            return new ClientCommand(taskManager);
        default:
            throw new InvalidCommandException("Unknown command: " + firstWord);
        }
    }
}
