package gigi.commands;

import gigi.exceptions.GigiException;
import gigi.storage.Storage;
import gigi.tasks.Task;
import gigi.tasks.Tasklist;
import gigi.ui.Ui;

/**
 * Represents a command to find tasks  in the task list that contain a specific keyword
 * */

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private final String keyword;

    /**
     * Constructs a FindCommand with the given keyword.
     *
     * @param keyword The keyword used to search for matching tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks that contain the specified keyword.
     * If matching tasks are found, it returns a formatted list of those tasks.
     * If no tasks match the keyword, it returns a message indicating no matches were found.
     *
     * @param tasks The task list to search in.
     * @param ui The UI component to display messages.
     * @param storage The storage system (not used in this command).
     * @return A message displaying the matching tasks or indicating no matches.
     * @throws GigiException If an error occurs while searching for tasks.
     */
    @Override
    public String execute(Tasklist tasks, Ui ui, Storage storage) throws GigiException {
        Tasklist matchingTasks = new Tasklist();
        matchingTasks = tasks.getMatchingTasks(this.keyword);

        if (matchingTasks.isEmpty()) {
            return ui.showMessage("MEOW!!! There are no matching tasks.");
        } else {
            int count = 0;
            StringBuilder result = new StringBuilder();
            result.append("Here are your matching tasks:\n");
            for (Task task : matchingTasks) {
                count++;
                result.append(count).append(". ");
                result.append(task.toString()).append("\n");
            }
            return result.toString();
        }
    }
}

