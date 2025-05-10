package eve.command;

import eve.util.Storage;
import eve.util.TaskList;

/**
 * Represents a command to display all available commands to the user.
 */
public class HelpCommand implements Command {
    /**
     * Displays all the available commands to the user.
     *
     * @param taskList ArrayList containing all the tasks.
     * @param storage Utils for storing information to data file.
     */
    public String execute(TaskList taskList, Storage storage) {
        StringBuilder message = new StringBuilder();
        message.append("Looks like you need help! Here are all my available commands:\n")
                .append("1. todo <description>: Adds a todo task to the list.\n")
                .append("2. deadline <description> /by <dd/mm/yyyy hh:mm>: Adds a "
                        + "deadline to the list.\n")
                .append("3. event <description> /from <dd/mm/yyyy hh:mm> /to <dd/mm/yyyy hh:mm>"
                        + ": Adds a event to the list.\n")
                .append("4. delete <task number>: Deletes specified task from the list.\n")
                .append("5. find <description>: Finds task with matching description.\n")
                .append("6. list: Lists all tasks in the list.\n")
                .append("7. mark <task number>: Marks specified task as done.\n ")
                .append("8. unmark <task number>: Unmarks specified task to be not done.\n ")
                .append("9. bye: Exits the application\n");
        return message.toString();
    }

    public boolean isExit() {
        return false;
    }

    public boolean isCloseWindow() {
        return false;
    }
}
