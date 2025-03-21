package tyler.command;

import tyler.storage.Storage;
import tyler.task.list.TaskList;
import tyler.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    public ListCommand() {
        super();
    }
    /**
     * Lists out all tasks in the list.
     *
     * @param tasks The list of tasks which has a task needing to be unmarked.
     * @param ui The UI object for any required printing.
     * @param storage The storage for I/O involving storing the tasks on the disk (unused).
     * @return The unmodified list.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            message.append("\t ").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        ui.showMessage(message.toString());
        return tasks;
    }
}
