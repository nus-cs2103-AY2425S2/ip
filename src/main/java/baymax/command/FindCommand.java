package baymax.command;

import baymax.Storage;
import baymax.TaskList;

/**
 * Represents a command that searches for tasks containing a specific keyword in their name.
 * The search is case-insensitive and returns a list of matching tasks.
 */
public class FindCommand extends Command {
    private static final String NO_MATCHING_LIST = "oh man, it seems like there are no matching tasks";
    private String keyword;

    /**
     * Constructs a {@code FindCommand} with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks that contain the keyword
     * and displaying the results to the user.
     *
     * @param tasks   The task list to search through.
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        TaskList matchingList = tasks.findTasks(keyword);
        return printMatchingList(matchingList);
    }
    /**
     * Prints the list of matching tasks based on the user's search query.
     * If no tasks match the search criteria, an error message is displayed.
     *
     * @param matchingTasks The {@code TaskList} containing tasks that match the search keyword.
     */
    private String printMatchingList(TaskList matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return NO_MATCHING_LIST;
        }
        return generateList(matchingTasks);
    }

    /**
     * Generates a formatted list of tasks.
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
