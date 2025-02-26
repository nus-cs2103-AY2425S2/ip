package nyanko.command;

import nyanko.storage.Storage;
import nyanko.task.Task;
import nyanko.task.TaskList;
import nyanko.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command to find tasks based on a keyword in the description.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to search for tasks that contain the given keyword.
     *
     * @param tasks   The task list to search through.
     * @param ui      The UI instance for user interaction.
     * @param storage The storage instance (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String findString = "Here are the matching tasks in your list:\n";

        ArrayList<Task> matchingTasks = new ArrayList<>();
        matchingTasks = tasks.findTasks(keyword);


        if (matchingTasks.isEmpty()) {
            ui.showMessage("stewpid! no matching tasks found!");
        } else {
            for (int i = 0; i < matchingTasks.size(); i++) {
                findString = findString + (i + 1) + ". " + matchingTasks.get(i) + "\n";
            }
            ui.showMessage(findString);
        }
    }
}
