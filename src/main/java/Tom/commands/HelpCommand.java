package Tom.commands;

import Tom.tasks.TaskList;

/**
 * Displays available commands.
 */
public class HelpCommand extends Command {
//help extension
    /**
     * Displays a list of available commands.
     *
     * @param tasks The TaskList instance.
     * @return The string representation of the command's response.
     */
    @Override
    public String execute(TaskList tasks) {
        return "Here are the commands available:\n"
                + " 1. list - Shows all tasks in the task list.\n"
                + " 2. todo <description> - Adds a new todo task.\n"
                + " 3. deadline <description> /by <yyyy-MM-dd> - Adds a deadline task.\n"
                + " 4. event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd> - Adds an event.\n"
                + " 5. mark <task number> - Marks a task as completed.\n"
                + " 6. unmark <task number> - Marks a task as not done.\n"
                + " 7. delete <task number> - Removes a task.\n"
                + " 8. find <keyword> - Finds all tasks containing <keyword>.\n"
                + " 9. help - Displays this help message.\n"
                + "10. bye - exit the application. \n";

    }
}