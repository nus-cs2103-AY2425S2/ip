package julie.command;

import julie.Storage;
import julie.TaskList;
import julie.UI;
import julie.exception.WrongFormatException;
import julie.task.Task;
import julie.task.ToDo;

/**
 * Represents a command to add a new ToDo task to the task list.
 */
public class AddToDoCommand extends Command {
    private final String description;

    /**
     * Constructs an {@code AddToDoCommand} with the given description.
     *
     * @param description The description of the ToDo task.
     */
    public AddToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add a new ToDo task.
     * The task is added to the task list, saved to storage, and acknowledged via the UI.
     *
     * @param tasks The task list where the new ToDo task will be added.
     * @param ui The user interface to display feedback to the user.
     * @param storage The storage system to persist the task list.
     * @throws WrongFormatException If the ToDo description is missing.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws WrongFormatException {
        if (description.trim().isEmpty()) {
            throw new WrongFormatException("Oops! Missing todo description!\nCorrect format: todo <description>");
        }
        Task todo = new ToDo(description);
        tasks.addTask(todo);
        storage.saveTasks(tasks.getAllTasks());
        ui.ackMessage(todo, tasks.size());
    }
}
