package bobandsteve.command;

import bobandsteve.exception.BobAndSteveException;
import bobandsteve.storage.Storage;
import bobandsteve.tasklist.TaskList;
import bobandsteve.ui.Ui;

/**
 * Command to display a list of all available commands in the application.
 * This command outputs a description of each available command along with its usage.
 */
public class HelpCommand extends Command {

    /**
     * Displays the list of available commands in the application.
     * This includes descriptions and usage for each command.
     */
    public String help() {
        return "Available Commands:\n" + "-------------------\n"
                + "HELP      - Displays the list of all commands.\n"
                + "FIND      - Find tasks base on keyword. Usage: find <keyword>\n"
                + "MARK      - Marks a task as completed. Usage: mark <task number>\n"
                + "UNMARK    - Unmarks a completed task. Usage: unmark <task number>\n"
                + "TODO      - Adds a todo. Usage: todo <task>\n"
                + "DEADLINE  - Adds a deadline. Usage: deadline <task> /by <date>\n"
                + "EVENT     - Adds an event. Usage: event <task> /from <start> /to <end>\n"
                + "LIST      - Displays all tasks.\n"
                + "SORT      - Sorts the task by date.\n"
                + "DELETE    - Deletes a task. Usage: delete <task number>\n"
                + "BYE       - Exits the program.\n"
                + "-------------------\n";
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BobAndSteveException {
        ui.printOutput(help());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        this.response = help();
        return this.response;
    }
}
