package marshmallow.commands;

import java.util.List;

import marshmallow.tasks.Task;
import marshmallow.tasks.TaskList;

/**
 * Represents a command to find tasks in the task list.
 */
public class ListFindTaskCommand extends Command {
    private String query;

    /**
     * Constructs a command to find tasks in the task list.
     *
     * @param query The query to search for.
     */
    public ListFindTaskCommand(String query) {
        this.query = query;
    }

    @Override
    public String handle(TaskList tasks) {
        assert tasks != null : "Task list should not be null";

        List<Task> filteredTasks = tasks.filterTasks(query);
        if (filteredTasks.isEmpty()) {
            return "No matching tasks found!";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        sb.append(new TaskList(filteredTasks).toString());
        return sb.toString();
    }
}
