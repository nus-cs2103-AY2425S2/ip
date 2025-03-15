package seedu.SirDuke.backend.command;

import seedu.SirDuke.backend.Storage;
import seedu.SirDuke.backend.ToDoList;

// @@author testing1234567891011121314
// Reused from https://github.com/testing1234567891011121314/ip
// with minor modifications


/**
 * The abstract Command class represents a command that SirDuke can execute.
 */
public abstract class Command {

    /**
     * This is the input string that may be used as a placeholder
     * for the un-parsed input fields of the command
     */
    String input;
    public Command(String input) {
        this.input = input;
    }

    /**
     * Check if the app should exit
     * @return true if the command is bye
     */
    public abstract boolean isExit();

    /**
     * Executes the current command
     * @return String representing reply about the command being executed
     */
    public abstract String execute(ToDoList toDoList, Storage storage);
}
