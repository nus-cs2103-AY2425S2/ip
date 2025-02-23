package bob.command;

import java.util.List;

import bob.task.Task;
import bob.task.TaskList;
import bob.util.Formatter;

/**
 * This class represents a command to find tasks that contain a search term.
 */
public class FindCommand extends Command {

    private String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public String execute() {
        List<Task> tasks = TaskList.getTasks();
        List<Task> filteredTasks = tasks.stream()
                .filter(task -> task.toString().contains(this.searchTerm))
                .toList();
        return Formatter.formatTasks(filteredTasks);
    }
}
