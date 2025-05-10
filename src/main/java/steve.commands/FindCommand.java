package steve.commands;

import steve.tasks.Find;
import steve.tasks.TaskManager;

/**
 * Represents a command that searches for tasks containing a specific keyword.
 */
public class FindCommand implements Command {
    private TaskManager taskManager;
    private String keyword;

    /**
     * Constructs a FindCommand with the specified task manager and user input.
     * Extracts the keyword from the user input.
     *
     * @param taskManager The task manager handling tasks.
     * @param userInput   The user input containing the search keyword.
     */
    public FindCommand(TaskManager taskManager, String userInput) {
        this.taskManager = taskManager;
        this.keyword = userInput.substring("find".length()).trim();
    }

    /**
     * Executes the find command by filtering tasks that match the keyword.
     */
    @Override
    public String execute() {
        Find find = new Find(this.keyword, this.taskManager);
        return find.filterString();
    }
}

