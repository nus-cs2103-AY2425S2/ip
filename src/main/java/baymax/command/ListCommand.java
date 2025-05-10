package baymax.command;

import baymax.Storage;
import baymax.TaskList;

/**
 * Represents a command that lists all tasks in the Baymax chatbot.
 * This command retrieves the current tasks from the task list and displays them to the user.
 */
public class ListCommand extends Command {
    private static final String EMPTY_LIST = "You are free! You currently have 0 tasks.";

    /**
     * Executes the list command by displaying all tasks in the task list.
     * This command does not modify the task list or storage.
     *
     * @param tasks   The task list containing tasks to be displayed.
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return printList(tasks);
    }
    /**
     * Prints the list of tasks.
     * If the task list is empty, an error message is displayed instead.
     *
     * @param tasks The list of tasks to be printed.
     */
    private String printList(TaskList tasks) {
        if (tasks.isEmpty()) {
            return EMPTY_LIST;
        }
        return generateList(tasks);
    }
    /**
     * Generates and prints a formatted list of tasks.
     * Each task is displayed with its corresponding index number.
     *
     * @param tasks The {@code TaskList} containing the tasks to be displayed.
     */
    private String generateList(TaskList tasks) {
        String output = new String();
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            int taskNumber = i + 1;
            output += taskNumber + ". " + tasks.getTasks().get(i) + "\n";
        }
        return output;
    }
}
