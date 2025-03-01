package bebop.command;
import bebop.exception.BebopException;
import bebop.task.TaskList;
import bebop.ui.Storage;
import bebop.ui.Ui;

/**
 * Check command for all invalid inputs.
 */
public class InvalidCommand extends Command {

    /**
     * Executes invalid command.
     *
     * @param tasks Tasklist storing tasks.
     * @param ui Ui to print commands.
     * @param storage stores task into Bebop.txt.
     *
     * @return string if the program will continue or not.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Sorry that's not a valid command :D, please use an appropriate format";
    }
}
