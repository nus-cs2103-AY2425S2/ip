package steve.commands;

import java.util.List;

import steve.tasks.Task;
import steve.tasks.TaskManager;

/**
 * Represents a command that displays the list of tasks in the task manager.
 */
public class ListCommand implements Command {
    private TaskManager taskManager;

    /**
     * Constructs a ListCommand with the specified task manager.
     *
     * @param taskManager The task manager that manages the list of tasks.
     */
    public ListCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Executes the list command by retrieving and displaying all tasks.
     */
    @Override
    public String execute() {
        List<Task> tasks = taskManager.getTasks();
        String border = ("_________________________\n");
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result = result + (i + 1) + tasks.get(i).list() + "\n";
        }
        return border + result + border;
    }
}
