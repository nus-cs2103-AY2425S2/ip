package bebop.command;

import bebop.exception.BebopException;
import bebop.task.TaskList;
import bebop.ui.Storage;
import bebop.ui.Ui;

/**
 * Exits the Chatbot.
 */

public class ExitCommand extends Command {

    /**
     * Exits the ui.
     *
     * @param tasks Tasklist storing tasks.
     * @param ui Ui to print commands.
     * @param storage stores task into Bebop.txt.
     *
     * @return string if the program will continue or not.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Have a nice day :D, see you soon!";
    }
}
