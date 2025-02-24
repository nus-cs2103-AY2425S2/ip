package boink;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import boink.exceptions.BoinkException;
import boink.exceptions.InvalidCommandException;
import boink.exceptions.InvalidTaskInputException;
import boink.tasks.Deadline;
import boink.tasks.Event;
import boink.tasks.Task;
import boink.tasks.ToDoTask;
import boink.utils.Utils;

/**
 * This class deals with making sense of the user command.
 */

public class Parser {

    /**
     * Parses user input entry into a command to be executed.
     * @param command User command.
     * @return Command to be executed.
     * @throws BoinkException If user enters input is empty or invalid
     */

    public static Command parseUserInput(String command) throws BoinkException {
        String[] parts = command.split(" ");
        String commandType = parts[0];

        switch (commandType) {
        case "":
            throw new BoinkException("Empty input. Please enter a valid command!");
        case "bye":
            return Command.BYE;
        case "list":
            return Command.LIST;
        case "unmark":
            return Command.UNMARK;
        case "mark":
            return Command.MARK;
        case "delete":
            return Command.DELETE;
        case "find":
            return Command.FIND;
        case "todo":
            return Command.TODO;
        case "deadline":
            return Command.DEADLINE;
        case "event":
            return Command.EVENT;
        case "archive":
            return Command.ARCHIVE;
        default:
            throw new InvalidCommandException("Unknown command. Please enter a valid command");
        }
    }

    /**
     * Creates task from user input.
     * @param input User input entry.
     * @return Task to be created from user input.
     * @throws BoinkException If user did not state task details or invalid format.
    */

    public static Task createTaskFromInput(String input) throws BoinkException {
        try {
            assert !input.isEmpty() : "User input should not be empty";
            String[] check = input.split(" ");
            if (check.length <= 1) {
                throw new InvalidTaskInputException("Task must have a name!");
            }

            String command = check[0];
            String details = input.substring(command.length()).trim();

            switch (command) {
            case "todo":
                return Parser.createToDoTask(details);
            case "deadline":
                return Parser.createDeadlineTask(details);
            case "event":
                return Parser.createEventTask(details);
            default:
                return null;
            }

        } catch (DateTimeParseException err) {
            throw new InvalidTaskInputException("Invalid date format!");
        }
    }

    private static ToDoTask createToDoTask(String details) {
        return new ToDoTask(details);
    }

    private static Deadline createDeadlineTask(String details) throws InvalidTaskInputException {
        String[] parts = details.split("/by ");
        if (parts.length < 2) {
            throw new InvalidTaskInputException("Deadline task must have a /by date!");
        }
        String name = parts[0].trim();
        LocalDateTime deadline = Utils.createDateTime(parts[1].trim());

        return new Deadline(name, deadline);
    }

    private static Event createEventTask(String details) throws InvalidTaskInputException {
        String[] parts = details.split("/from ");
        if (parts.length < 2 || !parts[1].contains("/to ")) {
            throw new InvalidTaskInputException("Event task must have /from and /to timestamps!");
        }
        String name = parts[0].trim();
        String[] timeParts = parts[1].split("/to ");
        LocalDateTime start = Utils.createDateTime(timeParts[0].trim());
        LocalDateTime end = Utils.createDateTime(timeParts[1].trim());

        return new Event(name, start, end);
    }

}
