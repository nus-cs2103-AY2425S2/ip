package adam.command;

import java.time.LocalDate;
import java.util.ArrayList;

import adam.core.TaskList;
import adam.exceptions.AdamException;
import adam.parser.Parser;

/**
 * Represents a command to list all tasks on a specific date.
 */
public class ListOnCommand extends Command {
    /** The date to list tasks on */
    private LocalDate date;

    /**
     * Constructor for ListOnCommand.
     *
     * @param input User input.
     * @throws AdamException If an error occurs while parsing the input.
     */
    public ListOnCommand(String input) throws AdamException {
        super();
        this.date = Parser.parseInputDate(input.split(" ")[1]);
    }

    /**
     * Checks if the input matches the command.
     *
     * @param input The input to check.
     * @return True if the input matches the command, false otherwise.
     */
    public static boolean isMatch(String input) {
        String[] inputParts = input.split(" ");
        return inputParts[0].equals("listOn") && inputParts.length == 2;
    }

    /**
     * Lists all tasks on the specified date.
     *
     * @param manager The task list to add the task to.
     * @return The output to show to the user.
     * @throws AdamException If an error occurs while adding the task.
     */
    @Override
    public String execute(TaskList manager) throws AdamException {
        ArrayList<String> outputs = manager.listAllOnDate(this.date);
        StringBuilder sb = new StringBuilder();
        for (String output : outputs) {
            sb.append(output).append("\n");
        }
        return sb.toString();
    }
}
