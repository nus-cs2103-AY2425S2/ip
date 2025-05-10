package baymax.command;

import java.io.IOException;

import baymax.Storage;
import baymax.TaskList;
import baymax.task.Deadline;


/**
 * Represents a command that adds a deadline task to the task list in the Baymax chatbot.
 * This command creates a new {@code Deadline} task and updates the task list and storage accordingly.
 */
public class AddDeadlineCommand extends AddCommand {
    private static final String ERROR_INVALID_DATE = "Invalid date format! Use 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.";
    private final String by;

    /**
     * Constructs an {@code AddDeadlineCommand} with the specified task name and due date.
     *
     * @param name The name of the deadline task.
     * @param by   The due date/time of the task in the accepted format.
     */
    public AddDeadlineCommand(String name, String by) {
        super(name);
        this.by = by;
    }

    /**
     * Executes the add deadline command by creating a new {@code Deadline} task,
     * adding it to the task list, saving the updated list to storage, and notifying the user via the UI.
     *
     * @param tasks   The task list to which the deadline task is added.
     * @param storage The storage handler to save the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws IOException {
        Deadline newTask = new Deadline(taskName, by);
        tasks.addTask(newTask);
        storage.saveTasks(tasks);
        return printNewDeadline(newTask);
    }

    /**
     * Returns a String when a new deadline task is added.
     *
     * @param newTask The newly added deadline task.
     */
    private String printNewDeadline(Deadline newTask) {
        return "added: " + newTask;
    }
}
