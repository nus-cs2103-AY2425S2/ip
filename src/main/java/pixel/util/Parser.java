package pixel.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import pixel.command.AddCommand;
import pixel.command.ClearCommand;
import pixel.command.Command;
import pixel.command.DeleteCommand;
import pixel.command.ExitCommand;
import pixel.command.ListCommand;
import pixel.command.SearchCommand;
import pixel.command.UpdateAllCommand;
import pixel.command.UpdateCommand;
import pixel.task.TaskType;

/**
 * Utility class which handles all functionality involving parsing input and formatting arguments.
 */
public class Parser {

    /**
     * Parses date and time from a String to a LocalDateTime object.
     * Accepts date/time String with the format (YYYY-MM-DD HH-MM), or (YYYY-MM-DD).
     * If time is not provided (latter format), time is assumed to be 00:00.
     *
     * @param input Date and time in String format
     * @return LocalDateTime object corresponding to the input date time
     * @throws PixelException If the input String does not conform to the accepted format
     */
    public static LocalDateTime parseDateTime(String input) throws PixelException {
        String[] comp = input.split(" ");
        if (comp.length > 2) {
            throw new PixelException("Please use the format YYYY-MM-DD HH:MM for the date and time!");
        }

        try {
            if (comp.length == 1) {
                // If time not provided, it is assumed to be 00:00
                return LocalDateTime.parse(comp[0] + "T00:00");
            }
            return LocalDateTime.parse(comp[0] + "T" + comp[1]);
        } catch (DateTimeParseException e) {
            throw new PixelException("Please use the format YYYY-MM-DD HH:MM for the date and time!");
        }
    }

    /**
     * Parses the components of the input command into the task details of a ToDo task,
     * namely task description.
     *
     * @param commandComponents Components of the input command
     * @return String array consisting of the task description
     * @throws PixelException If the task details required for a ToDo task are missing or invalid
     */
    public static String[] parseToDo(String[] commandComponents) throws PixelException {
        StringBuilder desc = new StringBuilder();

        for (int i = 1; i < commandComponents.length; i++) {
            desc.append(commandComponents[i]).append(" ");
        }

        if (desc.toString().isEmpty()) {
            throw new PixelException("Please include a description for the ToDo!");
        }

        return new String[] { desc.toString().strip() };
    }

    /**
     * Parses the components of the input command into the task details of a Deadline task,
     * namely task description and deadline.
     *
     * @param commandComponents Components of the input command
     * @return String array consisting of the task description
     * @throws PixelException If the task details required for a Deadline task are missing or invalid
     */
    public static String[] parseDeadline(String[] commandComponents) throws PixelException {
        StringBuilder desc = new StringBuilder();
        StringBuilder dueBy = new StringBuilder();
        StringBuilder pointer = desc;

        for (int i = 1; i < commandComponents.length; i++) {
            if (commandComponents[i].equalsIgnoreCase("/by")) {
                pointer = dueBy;
            } else {
                pointer.append(commandComponents[i]).append(" ");
            }
        }

        if (desc.toString().isEmpty()) {
            throw new PixelException("Please include a description for the Deadline!");
        } else if (dueBy.toString().isEmpty()) {
            throw new PixelException("Please set a deadline using '/by' followed by a date/time!");
        }

        return new String[] { desc.toString().strip(), dueBy.toString().strip() };
    }

    /**
     * Parses the components of the input command into the task details of an Event task,
     * namely task description, start date/time and end date/time.
     *
     * @param commandComponents Components of the input command
     * @return String array consisting of the task description
     * @throws PixelException If the task details required for an Event task are missing or invalid
     */
    public static String[] parseEvent(String[] commandComponents) throws PixelException {
        StringBuilder desc = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();
        StringBuilder pointer = desc;

        for (int i = 1; i < commandComponents.length; i++) {
            if (commandComponents[i].equalsIgnoreCase("/from")) {
                pointer = from;
            } else if (commandComponents[i].equalsIgnoreCase("/to")) {
                pointer = to;
            } else {
                pointer.append(commandComponents[i]).append(" ");
            }
        }

        if (desc.toString().isEmpty()) {
            throw new PixelException("Please include a description for the Event!");
        } else if (from.toString().isEmpty()) {
            throw new PixelException("Please set a starting date/time using '/from' followed by a date/time!");
        } else if (to.toString().isEmpty()) {
            throw new PixelException("Please set a ending date/time using '/to' followed by a date/time!");
        }

        return new String[] { desc.toString().strip(), from.toString().strip(), to.toString().strip() };
    }

    /**
     * Parses the input command by splitting it into words, then checking the intended command type by checking
     * the first word of the command.
     * If the first word is a recognized keyword, a Command corresponding to the keyword is constructed and returned.
     *
     * @param input Input command read by the Ui class from standard input
     * @return A Command corresponding to the keyword, if recognized
     * @throws PixelException If the first word of the command is not a recognized keyword,
     *                        or if a non-number is provided for mark and unmark commands
     */
    public static Command parseFullCommand(String input) throws PixelException {
        String[] components = input.split("\\s+");
        String keyword = components[0].toLowerCase();
        try {
            return switch (keyword) {
                case "bye" -> new ExitCommand();
                case "list" -> new ListCommand();
                case "todo" -> new AddCommand(TaskType.TODO, components);
                case "deadline" -> new AddCommand(TaskType.DEADLINE, components);
                case "event" -> new AddCommand(TaskType.EVENT, components);
                case "mark" -> new UpdateCommand(true, Integer.parseInt(components[1]) - 1);
                case "unmark" -> new UpdateCommand(false, Integer.parseInt(components[1]) - 1);
                case "delete" -> new DeleteCommand(Integer.parseInt(components[1]) - 1);
                case "find" -> new SearchCommand(input.substring(5));
                case "clear" -> new ClearCommand();
                case "markall" -> new UpdateAllCommand(true, Integer.parseInt(components[1]) - 1,
                        Integer.parseInt(components[2]) - 1);
                case "unmarkall" -> new UpdateAllCommand(false, Integer.parseInt(components[1]) - 1,
                        Integer.parseInt(components[2]) - 1);
                default -> throw new PixelException("Sorry, I'm not sure what that means...");
            };
        } catch (NumberFormatException e) {
            throw new PixelException("Please input a valid task number!");
        }
    }
}
