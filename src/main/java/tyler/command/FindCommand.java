package tyler.command;

import tyler.storage.Storage;
import tyler.task.Task;
import tyler.task.list.TaskList;
import tyler.ui.Ui;

/**
 * Represents a command which finds and lists all tasks containing a certain keyword.
  */
public class FindCommand extends Command {
    private final String[] tokens;

    public FindCommand(String[] tokens) {
        super();
        this.tokens = tokens;
    }

    /**
     * Display all tasks which contain a certain keyword.
     *
     * @param tasks The list of tasks which is searched for the keyword.
     * @param ui The UI object for any required printing.
     * @param storage The storage object to handle I/O operations for the list of tasks (unused).
     * @return The unmodified list.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        int i = 1;
        try {
            String keyword = tokens[1];
            StringBuilder message = new StringBuilder();
            message.append("\t ").append("Here are the matching tasks in your list:\n");
            for (Task t : tasks) {
                if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                    message.append("\t ").append(i).append(". ").append(t).append("\n");
                    i++;
                }
            }
            ui.showMessage(message.toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage("\t !!Please provide the correct number of arguments!!");
        }
        return tasks;
    }
}
