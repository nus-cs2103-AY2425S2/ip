package tom.command;

import tom.storage.Storage;
import tom.tasklist.TaskList;
import tom.ui.Ui;

/**
 * Represents a command to display help information.
 */
public class HelpCommand extends Command {

    /**
     * Executes the command to display help information.
     *
     * @param tasks The task list.
     * @param ui The UI for interacting with the user.
     * @param storage The storage for saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String helpMessage = "Here are the available commands:\n"
                + "1. todo <description> - Adds a todo task\n"
                + "2. deadline <description> /by < - Adds a deadline task\n"
                + "3. event <description> /from <date> /to <date> - Adds an event task\n"
                + "4. list - Lists all tasks\n"
                + "5. mark <task number> - Marks a task as done\n"
                + "6. unmark <task number> - Marks a task as undone\n"
                + "7. delete <task number> - Deletes a task\n"
                + "8. find <keyword> - Finds tasks containing the keyword\n"
                + "9. help - Displays this help message\n"
                + "10. bye - Exits the application"
                + "11. save - Saves the current task list to disk"
                + "12. load - Loads the task list from disk";
        ui.showMessage(id, helpMessage);
    }

    /**
     * Indicates whether this command exits the application.
     *
     * @return false as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
