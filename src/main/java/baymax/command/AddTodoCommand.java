package baymax.command;

import java.io.IOException;

import baymax.Storage;
import baymax.TaskList;
import baymax.task.Task;
import baymax.task.Todo;

/**
 * Represents a command that adds a to-do task to the task list in the Baymax chatbot.
 * This command creates a new {@code Todo} task and updates the task list and storage accordingly.
 */
public class AddTodoCommand extends AddCommand {

    /**
     * Constructs an {@code AddTodoCommand} with the specified task name.
     *
     * @param taskName The name of the to-do task.
     */
    public AddTodoCommand(String taskName) {
        super(taskName);
    }

    /**
     * Executes the add to-do command by creating a new {@code Todo} task,
     * adding it to the task list, saving the updated list to storage, and notifying the user via the UI.
     *
     * @param tasks   The task list to which the to-do task is added.
     * @param storage The storage handler to save the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws IOException {
        Todo newTask = new Todo(this.taskName);
        tasks.addTask(newTask);
        storage.saveTasks(tasks);
        return printNewTodo(newTask);
    }
    /**
     * Returns a String when a new to-do task is added.
     *
     * @param newTask The newly added to-do task.
     */
    private String printNewTodo(Task newTask) {
        return "added: " + newTask;
    }
}

