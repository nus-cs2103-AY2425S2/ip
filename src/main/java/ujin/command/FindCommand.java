package ujin.command;

import ujin.task.Task;
import ujin.task.TaskList;
import ujin.ui.Ui;

/**
 * Command that handles finding tasks that match a given keyword in their description.
 * This class extends {@link Command} and allows searching through a list of tasks
 * to find those whose description contains the specified keyword.
 */
public class FindCommand extends Command {

    private final String KEYWORD;
    private final TaskList MATCH_LIST = new TaskList();

    /**
     * Constructor for the FindCommand.
     * Initializes the command with the provided keyword to search for in task descriptions.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.KEYWORD = keyword;
    }

    /**
     * Executes the find command by searching through the given task list and finding tasks
     * whose descriptions contain the specified keyword. Matching tasks are added to a list
     * and displayed to the user via the {@link Ui#findTasks(TaskList)} method.
     *
     * @param taskList The list of tasks to search through.
     * @param ui The UI instance used to display the results to the user.
     */
    public String execute(TaskList taskList, Ui ui) {
        int size = taskList.size();
        assert size >= 0;
        for (int i = 0; i < size; i++) {
            Task task = taskList.get(i);
            String description = task.description();
            if (description.contains(KEYWORD)) {
                MATCH_LIST.add(task);
            }
        }
        return ui.findTasks(MATCH_LIST);
    }
}
