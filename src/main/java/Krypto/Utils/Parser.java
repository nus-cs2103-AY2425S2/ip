package Krypto.Utils;
import java.time.format.DateTimeParseException;

import Krypto.Commands.*;

import Krypto.Exceptions.KryptoExceptions;
import Krypto.Exceptions.IncompleteCommand;
import Krypto.Exceptions.InvalidCommand;
import Krypto.Exceptions.ToDoException;

import Krypto.Task.Task;
import Krypto.Task.Deadline;
import Krypto.Task.Event;
import Krypto.Task.ToDo;

/**
 * Parses user input and converts it into executable commands.
 */
public class Parser {
    private static final String DEADLINE_FORMAT = "deadline <task description>/yyyy-MM-dd HH:mm";
    private static final String EVENT_FORMAT = "event <task description>/yyyy-MM-dd HH:mm/yyyy-MM-dd HH:mm";
    private static String MARK_FORMAT = "mark <index>";
    private static String UNMARK_FORMAT = "unmark <index>";
    private static String DELETE_FORMAT = "delete <index>";
    private static String SHOW_FORMAT = "show yyyy-MM-dd";
    private static String FIND_FORMAT = "find <keyword>";

    private static String RESCHEDULE_FORMAT = "reschedule <index> ->yyyy-MM-dd HH:mm,yyyy-MM-dd HH:mm";

    /**
     * Returns the format string for a given command type.
     *
     * @param commandType The type of command.
     * @return The format string for the command.
     */
    private static String getFormat(String commandType) {
        return switch (commandType) {
            case "mark" -> MARK_FORMAT;
            case "unmark" -> UNMARK_FORMAT;
            case "delete" -> DELETE_FORMAT;
            case "show" -> SHOW_FORMAT;
            case "find" -> FIND_FORMAT;
            case "deadline" -> DEADLINE_FORMAT;
            case "event" -> EVENT_FORMAT;
            case "reschedule" -> RESCHEDULE_FORMAT;
            default -> "";
        };
    }
    public Parser() {}

    /**
     * Parses the given user input and returns the corresponding command.
     *
     * @param prompt The user input string.
     * @return The parsed command.
     * @throws KryptoExceptions If the command is invalid or incomplete.
     */
    public static Command parse(String prompt) throws KryptoExceptions {
        String[] split = prompt.split(" ");
        String first = split[0];
        checkLength(first, split);
        return switch (first) {
            case "bye" -> new ExitCommand();
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(Integer.parseInt(split[1]) - 1);
            case "unmark" -> new UnmarkCommand(Integer.parseInt(split[1]) - 1);
            case "delete" -> new DeleteCommand(Integer.parseInt(split[1]) - 1);
            case "show" -> new ShowCommand(split[1]);
            case "find" -> new FindCommand(split[1]);
            case "reschedule" -> new RescheduleCommand(split[1], prompt.split("->")[1]);
            case "todo", "event", "deadline" -> createTaskCommand(prompt, split);
            default -> throw new InvalidCommand(first);
        };
    }
    private static void checkLength(String first, String[] split) throws KryptoExceptions {
        boolean isTwo = first.equals("mark") || first.equals("unmark")
                || first.equals("delete") || first.equals("show") || first.equals("find");
        boolean isFour = first.equals("reschedule");
        int len = split.length;
        if (isFour) {
            if (len < 4) {
                throw new IncompleteCommand(first, getFormat(first));
            }
        } else if (isTwo) {
            if (len < 2) {
                throw new IncompleteCommand(first, getFormat(first));
            }
        }
    }

    /**
     * Creates a task-related command based on user input.
     *
     * @param prompt The user input string.
     * @param split  The split components of the user input.
     * @return The parsed command.
     * @throws KryptoExceptions If the command is invalid or incomplete.
     */
    private static Command createTaskCommand(String prompt, String[] split) throws KryptoExceptions {
        String type = split[0];
        String[] parts = prompt.split("/");
        Task newTask;
        switch (type) {
            case "todo":
                if (parts.length > 1 || split.length <= 1) {
                    throw new ToDoException();
                }
                newTask = new ToDo(prompt);
                break;
            case "deadline":
                if (parts.length != 2) {
                    throw new IncompleteCommand("deadline", DEADLINE_FORMAT);
                }
                try {
                    newTask = new Deadline(prompt, parts[1]);
                } catch (DateTimeParseException e) {
                    throw new KryptoExceptions("Invalid date format! Use /yyyy-MM-dd HH:mm (e.g., 2-12-2019 18:00).");
                }
                break;
            case "event":
                if (parts.length != 3) {
                    throw new IncompleteCommand("event", EVENT_FORMAT);
                }
                try {
                    newTask = new Event(prompt, parts[1], parts[2]);
                } catch (DateTimeParseException e) {
                    throw new KryptoExceptions("Invalid date format! Use yyyy/MM/dd HH:mm (e.g., 2/12/2019 18:00).");
                }
                break;
            default:
                throw new InvalidCommand(type);
        }
        return new AddCommand(newTask);
    }

    /**
     * Parses a storage entry and returns the corresponding task.
     *
     * @param line A line from the storage file.
     * @return The parsed task.
     */
    public static Task parseStorage(String line) {
        String[] parts = line.split(" \\| ");
        Task task;
        String isMarked = parts[1];
        task = switch (parts[0]) {
            case "T" -> new ToDo("todo " + parts[2]);
            case "D" -> new Deadline("deadline " + parts[2], parts[3]);
            case "E" -> new Event("event " + parts[2], parts[3], parts[4]);
            default -> new Task("");
        };
        if (isMarked.equals("X")) {
            task.markTask(false);
        }
        return task;
    }
}
