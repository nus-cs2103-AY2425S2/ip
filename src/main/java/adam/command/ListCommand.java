package adam.command;

import java.util.ArrayList;

import adam.core.TaskList;
import adam.exceptions.AdamException;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Checks if the input matches the command.
     *
     * @param input The input to check.
     * @return True if the input matches the command, false otherwise.
     */
    public static boolean isMatch(String input) {
        return input.equals("list");
    }

    /**
     * Lists all tasks in the task list.
     *
     * @param manager The task list to add the task to.
     * @return The output to show to the user.
     * @throws AdamException If an error occurs while adding the task.
     */
    @Override
    public String execute(TaskList manager) throws AdamException {
        ArrayList<String> outputs = manager.listAll();
        StringBuilder sb = new StringBuilder();
        for (String output : outputs) {
            sb.append(output).append("\n");
        }
        return sb.toString();
    }
}
