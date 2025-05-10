package nickiminaj;

import nickiminaj.command.AddCommand;
import nickiminaj.command.ByeCommand;
import nickiminaj.command.DeleteCommand;
import nickiminaj.command.ListCommand;
import nickiminaj.command.MarkCommand;
import nickiminaj.command.UnmarkCommand;
import nickiminaj.command.InspireCommand;
import nickiminaj.command.Command;
import nickiminaj.command.WelcomeCommand;
import nickiminaj.command.ViewScheduleCommand;
import nickiminaj.tasks.Deadline;
import nickiminaj.tasks.Event;
import nickiminaj.tasks.Todo;

/**
 * The Parser class interprets user commands and returns the corresponding Command object.
 */
public class Parser {

    /**
     * Parses the user input and returns the appropriate Command.
     *
     * @param input The user input string.
     * @return The corresponding Command object.
     * @throws NickiMinajException If the command is invalid.
     */
    public static Command parse(String input) throws NickiMinajException {
        assert input != null : "Error: Input cannot be null!";

        String[] parts = input.split(" ", 2);
        assert parts.length > 0 : "Error: Command cannot be empty!";

        String commandType = parts[0];
        String argument = (parts.length > 1) ? parts[1].trim() : "";

        assert !commandType.isEmpty() : "Error: Command type should not be empty!";

        switch (commandType) {
        case "bye":
            return new ByeCommand();
            // Fallthrough
        case "view":
            return new ViewScheduleCommand(argument);
            //Fallthrough
        case "hey":
        case "hi":
        case "hello":
            return new WelcomeCommand();
        case "list":
            return new ListCommand();
            // Fallthrough
        case "mark":
        case "unmark":
        case "delete":
            return handleTaskModification(commandType, argument);
            // Fallthrough
        case "todo":
            return handleTodo(argument);
            // Fallthrough
        case "deadline":
            return handleDeadline(argument);
            // Fallthrough
        case "event":
            return handleEvent(argument);
            // Fallthrough
        case "inspire":
            return new InspireCommand();
        // Fallthrough
        default:
            throw new NickiMinajException("I don't even know what that means... but it sounds mad iconic.");
        }
    }

    /**
     * Handles commands related to marking, unmarking, and deleting tasks.
     *
     * @param commandType The type of task modification command.
     * @param argument The task index as a string.
     * @return The corresponding Command object.
     * @throws NickiMinajException If the argument is invalid.
     */
    private static Command handleTaskModification(String commandType, String argument) throws NickiMinajException {
        if (argument.isEmpty()) {
            throw new NickiMinajException("Oops! You need to provide a task number.");
        }
        try {
            int taskIndex = Integer.parseInt(argument) - 1;
            assert taskIndex >= 0 : "Barb! Task index must be non-negative!";
            return switch (commandType) {
                case "mark" -> new MarkCommand(taskIndex);
                case "unmark" -> new UnmarkCommand(taskIndex);
                case "delete" -> new DeleteCommand(taskIndex);
                default -> throw new NickiMinajException("I don't even know what that means... but it sounds mad iconic.");
            };
        } catch (NumberFormatException e) {
            throw new NickiMinajException("Enter a valid number, Barb.");
        }
    }

    /**
     * Handles the creation of a Todo task.
     *
     * @param argument The description of the task.
     * @return An AddCommand with the new Todo task.
     * @throws NickiMinajException If the description is empty.
     */
    private static Command handleTodo(String argument) throws NickiMinajException {
        if (argument.isEmpty()) {
            throw new NickiMinajException("Barb! you forgot description.");
        }
        return new AddCommand(new Todo(argument));
    }

    /**
     * Handles the creation of a Deadline task.
     *
     * @param argument The description and deadline of the task.
     * @return An AddCommand with the new Deadline task.
     * @throws NickiMinajException If the format is incorrect.
     */
    private static Command handleDeadline(String argument) throws NickiMinajException {
        if (!argument.contains(" /by ")) {
            throw new NickiMinajException("Write it like this, queen:\n deadline TASK /by dd/MM/yyyy HHmm.");
        }
        String[] parts = argument.split(" /by ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new NickiMinajException("Oops! You must specify a deadline date.");
        }
        return new AddCommand(new Deadline(parts[0], parts[1]));
    }

    /**
     * Handles the creation of an Event task.
     *
     * @param argument The description, start time, and end time of the event.
     * @return An AddCommand with the new Event task.
     * @throws NickiMinajException If the format is incorrect.
     */
    private static Command handleEvent(String argument) throws NickiMinajException {
        if (!argument.contains(" /from ") || !argument.contains(" /to ")) {
            throw new NickiMinajException("Write it like this, queen:" +
                    "\nevent TASK /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm.");
        }
        String[] parts = argument.split(" /from | /to ", 3);
        if (parts.length < 3 || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new NickiMinajException("Barb! you forgot start and end times.");
        }
        return new AddCommand(new Event(parts[0], parts[1], parts[2]));
    }
}
