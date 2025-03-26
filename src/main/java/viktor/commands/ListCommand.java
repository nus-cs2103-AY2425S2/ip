package viktor.commands;

import java.util.stream.IntStream;

import viktor.exceptions.ViktorException;
import viktor.tasks.TaskList;

/**
 * Command to list all tasks in the task list.
 */
public class ListCommand implements Commandable {
    private TaskList tasks;

    /**
     * Constructs the ListCommand with a task list.
     *
     * @param tasks The list of tasks.
     */
    public ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Executes the command to list all tasks in the task list.
     *
     * @throws ViktorException If the input is invalid or there is an error with task creation.
     */
    @Override
    public String execute() throws ViktorException {
        if (tasks.isEmpty()) {
            return "No tasks added yet!";
        }

        String taskListString = IntStream.range(0, tasks.size())
            .mapToObj(i -> (i + 1) + ". " + tasks.getTask(i).toString())
            .reduce((a, b) -> a + "\n" + b)
            .orElse("");

        return "Your tasks await you:\n" + taskListString
                + "\n\nCurrently, you have " + tasks.size() + " tasks in the list.";
    }
}
