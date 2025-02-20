package adam.command;

import java.util.ArrayList;

import adam.core.TaskList;
import adam.exceptions.AdamException;

/**
 * Represents a command to find tasks that match a given query.
 * This command will search through the task list for all tasks
 * that contain the specified query and print them to the user.
 */
public class FindCommand extends Command {
    private String query;

    /**
     * Constructor for FindCommand.
     *
     * @param input User input.
     */
    public FindCommand(String input) {
        super();
        this.query = input.substring(5);
    }

    /**
     * Checks if the input matches the command.
     *
     * @param input The input to check.
     * @return True if the input matches the command, false otherwise.
     */
    public static boolean isMatch(String input) {
        return input.startsWith("find ");
    }

    /**
     * Finds all tasks that match the query and outputs them to the user.
     *
     * @param manager The task list to search through.
     * @return The output to show to the user.
     * @throws AdamException If an error occurs while searching for tasks.
     */
    @Override
    public String execute(TaskList manager) throws AdamException {
        ArrayList<String> outputs = manager.listMatches(this.query);
        StringBuilder sb = new StringBuilder();
        for (String output : outputs) {
            sb.append(output).append("\n");
        }
        return sb.toString();
    }
}
