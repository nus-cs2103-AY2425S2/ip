package jimmy.commands;

import jimmy.JimmyException;
import jimmy.Storage;
import jimmy.Ui;
import jimmy.tasks.Task;
import jimmy.tasks.TaskList;
import jimmy.tasks.Todo;

/**
 * The {@code AddTodoCommand} class represents a command to add a new to-do task
 * to the task list. It validates the task description, adds the task, updates storage,
 * and provides feedback to the user.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Constructs an {@code AddTodoCommand} with the specified task description.
     *
     * @param description the description of the to-do task.
     * @throws JimmyException if the description is null or blank.
     */
    public AddTodoCommand(String description) throws JimmyException {
        super();
        if (description == null || description.isBlank()) {
            throw new JimmyException("The description of a todo cannot be empty.");
        }
        this.description = description;
    }

    /**
     * Executes the add-to-do command by creating a new to-do task, adding it to the task list,
     * saving the updated task list to storage, and notifying the user.
     *
     * @param tasks   the {@code TaskList} containing all current tasks.
     * @param ui      the {@code Ui} instance for displaying messages to the user.
     * @param storage the {@code Storage} instance responsible for saving tasks.
     * @throws JimmyException if an error occurs while saving the task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JimmyException {
        Task todo = new Todo(description);
        tasks.addTask(todo);
        storage.save(tasks.getAllTasks());
        ui.showMessage("Got it. I've added this task:\n  " + todo
                       + "\nNow you have " + tasks.size() + " tasks in the list.");
        return "Got it. I've added this task:\n  " + todo
                       + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Indicates whether this command should terminate the program.
     *
     * @return {@code false} as the add-to-do command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
