package commands;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Represents a command to list the added tasks.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {

        if (tasks.getTasks().isEmpty()) {
            return "There are no tasks in this list.";
        } else {
                StringBuilder response = new StringBuilder("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    // Retrieve each task using tasks.get(i) and append it
                    Task task = tasks.get(i);
                    response.append("\n ").append(i + 1).append(". ").append(task.toString());
                }
                return response.toString();
        }
    }
}
