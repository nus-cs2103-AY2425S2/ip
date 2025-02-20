package command;

import core.TaskList;
import storage.Storage;
import ui.Ui;
import task.Task;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Represents a command to find tasks by keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, searching for tasks that contain the keyword.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @return The response to the user command.
     */
    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchedTasks = tasks.getTasks().stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());

        if (matchedTasks.isEmpty()) {
            return "Sorry baby, no matching tasks found.";
        } else {
            StringBuilder result = new StringBuilder("Here are the matching tasks:\n");
            for (int i = 0; i < matchedTasks.size(); i++) {
                result.append(i + 1).append(". ").append(matchedTasks.get(i)).append("\n");
            }
            return result.toString();
        }
    }
}