package clank.command;

import java.util.ArrayList;

import clank.exception.ClankException;
import clank.task.Task;
import clank.task.TaskList;
import clank.utility.Storage;
import clank.utility.Ui;

/**
 * Represents a command to find tasks that contain a specific keyword in their description.
 */
public class FindCommand extends Command {
    private String[] keywords;

    /**
     * Constructs a {@code FindCommand} with the specified keyword.
     *
     * @param input The raw user input containing the keyword.
     * @throws ClankException If the input format is incorrect or the keyword is missing.
     */
    public FindCommand(String input) throws ClankException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new ClankException(ClankException.ErrorType.INVALID_FORMAT, "find <keyword>");
        }
        this.keywords = parts;
    }

    /**
     * Executes the find command, searching for tasks that contain the keyword
     * and displaying the results to the user.
     *
     * @param taskList The task list to search within.
     * @param ui The UI instance to display the matching tasks.
     * @param storage The storage system (not modified in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null : "TaskList should not be null.";
        assert ui != null : "Ui should not be null.";

        ArrayList<Task> matchingTasks = taskList.findTasksWithKeywords(keywords);
        ui.showMatchingTasks(matchingTasks);
    }
}
