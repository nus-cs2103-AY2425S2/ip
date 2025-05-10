package helix.command;

import java.util.List;
import java.util.stream.Collectors;

import helix.enums.CommandType;
import helix.task.Task;
import helix.task.TaskList;
import helix.ui.ConsoleUi;

/**
 * Represents a command to search for tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword the keyword to search for in task descriptions
     */
    public FindCommand(String keyword) {
        super(CommandType.FIND);
        this.keyword = keyword;
    }

    /**
     * Executes the find command by filtering tasks in the task list that contain
     * the specified keyword and displaying them to the user.
     *
     * @param taskList the TaskList to search for matching tasks
     * @param consoleUi the ConsoleUi component used to display messages to the user
     */
    @Override
    public void execute(TaskList taskList, ConsoleUi consoleUi) {
        List<Task> matchingTasks = findMatchingTasks(taskList);
        consoleUi.showMatchingTasks(matchingTasks);
    }

    /**
     * Retrieves tasks that contain the specified keyword.
     *
     * @param taskList the {@code TaskList} containing the tasks to search
     * @return a list of tasks that match the search keyword
     */
    private List<Task> findMatchingTasks(TaskList taskList) {
        return taskList.getTasks().stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
