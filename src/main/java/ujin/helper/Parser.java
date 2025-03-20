package ujin.helper;

import ujin.Contact;
import ujin.UjinException;
import ujin.command.AddCommand;
import ujin.command.Command;
import ujin.command.DeleteCommand;
import ujin.command.ExitCommand;
import ujin.command.FindCommand;
import ujin.command.ListCommand;
import ujin.command.MarkerCommand;
import ujin.command.WrongTextCommand;
import ujin.task.Deadline;
import ujin.task.Event;
import ujin.task.Task;
import ujin.task.Todo;
import ujin.ui.Ui;

/**
 * The {@code Parser} class is responsible for parsing user input commands
 * and returning the appropriate {@link Command} object based on the input.
 * It can handle various types of commands such as adding tasks, marking tasks,
 * deleting tasks, and others. This class uses a simple string parsing technique
 * and splits the command into tokens to determine the action to perform.
 */
public class Parser {

    /**
     * Parses the given command string and returns a corresponding {@link Command}
     * object. The command string is split into tokens to identify the task type
     * and create the appropriate {@link Command} subclass instance.
     *
     * @param command The command string to parse.
     * @param ui The {@link Ui} object used to display error messages.
     * @return A {@link Command} object that corresponds to the parsed command.
     * @throws UjinException If the command is unrecognized or invalid.
     */
    public static Command parse(String command, Ui ui) throws UjinException {
        String[] tokens = command.split(" ", 2);
        switch (tokens[0]) {
        case "todo" -> {
            if (tokens.length != 2) {
                return new WrongTextCommand();
            }
            Task newTask = new Todo(tokens[1]);
            return new AddCommand(newTask);
        }
        case "deadline" -> {
            if (tokens.length != 2) {
                return new WrongTextCommand();
            }
            System.out.println(tokens[1]);
            String[] desc = parseInfo(tokens[1]);
            System.out.println(desc[1]);
            Task newTask = new Deadline(desc[0], desc[1]);
            return new AddCommand(newTask);
        }
        case "event" -> {
            if (tokens.length != 2) {
                return new WrongTextCommand();
            }
            String[] desc = parseInfo(tokens[1]);
            if (desc.length != 3) {
                return new WrongTextCommand();
            }
            Task newTask = new Event(desc[0], desc[1], desc[2]);
            return new AddCommand(newTask);
        }
        case "mark" -> {
            try {
                int index = Integer.parseInt(tokens[1]);
                return new MarkerCommand(true, index);
            } catch (Exception e) {
                return new WrongTextCommand();
            }
        }
        case "unmark" -> {
            try {
                int index = Integer.parseInt(tokens[1]);
                return new MarkerCommand(false, index);
            } catch (Exception e) {
                ui.showError(e.getMessage());
                return new WrongTextCommand();
            }
        }
        case "delete" -> {
            try {
                int index = Integer.parseInt(tokens[1]);
                return new DeleteCommand(index);
            } catch (Exception e) {
                ui.showError(e.getMessage());
                return new WrongTextCommand();
            }
        }
        case "list" -> {
            return new ListCommand();
        }
        case "bye" -> {
            return new ExitCommand();
        }
        case "find" -> {
            return new FindCommand(tokens[1]);
        }
        default -> {
            return new WrongTextCommand();
//            throw new UjinException("Please check the first word! It should be about the task!");
        }
        }
    }

    /**
     * Helper method that splits a string containing task information into
     * components based on a specific delimiter.
     *
     * @param string The input string to parse.
     * @return An array of strings containing the task information.
     */
    public static String[] parseInfo(String string) {
        return string.split(" /\\S+ ");
    }
}
