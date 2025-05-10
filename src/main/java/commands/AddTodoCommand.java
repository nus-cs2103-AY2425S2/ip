package commands;

import java.io.IOException;

import storage.Storage;
import task.TaskList;
import task.Todo;
import ui.Ui;

/**
 * Represents a command to add a Todo task.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Constructs an AddTodoCommand.
     * @param input The full user input containing the todo description.
     */
    public AddTodoCommand(String input) {
        this.description = input.replaceFirst("todo ", "").trim();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (description.isEmpty()) {
            throw new IOException("Todo description cannot be empty.");
        }
        tasks.add(new Todo(description, false));
        return "Got it. I've added this task:\n   " + tasks.getLastTask()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
