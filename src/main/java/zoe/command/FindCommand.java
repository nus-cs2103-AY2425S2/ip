package zoe.command;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import zoe.storage.Storage;
import zoe.task.Task;
import zoe.task.TaskList;
import zoe.ui.Ui;

/**
 * Represents a command to search for tasks whose names contain a given keyword.
 * <p>
 * When executed, this command filters the list of tasks for those that include the
 * specified keyword in their name and then displays the matching tasks to the user.
 * </p>
 *
 * <p>
 * Usage Example:
 * <pre>
 *   find meeting
 * </pre>
 * </p>
 *
 * @see Command
 */
@Getter
@AllArgsConstructor
public class FindCommand implements Command {
    public static final String COMMAND_WORD = "find";
    private final String keyword;

    /**
     * Executes the find command.
     * <p>
     * This method filters the {@code taskList} by checking each task's name for the
     * presence of the {@code keyword}. The matching tasks are then displayed to the user
     * via the {@code ui} component.
     * </p>
     *
     * @param storage the storage component (not used in this command)
     * @param taskList the list of tasks to search through
     * @param ui the user interface component used to display the results
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        // Use the Streams feature of Java
        List<Task> matchingTasks = taskList.stream()
                .filter(task -> task.getTaskName().contains(keyword))
                .collect(Collectors.toList());
        ui.showList(matchingTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
