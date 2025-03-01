package bebop.command;

import bebop.exception.BebopException;
import bebop.task.TaskList;
import bebop.ui.Storage;
import bebop.ui.Ui;
/**
 * Abstract class for all commands.
 */

public abstract class Command {

    /**
     * Executes command.
     *
     * @param tasks Tasklist storing tasks.
     * @param ui Ui to print commands.
     * @param storage stores task into Bebop.txt.
     *
     * @return string if the program will continue or not.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Check if the string is an Integer.
     *
     * @param str string to be converted to integer.
     * @return boolean to check if isInteger.
     */
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
