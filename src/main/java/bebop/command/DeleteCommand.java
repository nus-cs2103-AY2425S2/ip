package bebop.command;
import bebop.exception.BebopException;
import bebop.task.TaskList;
import bebop.ui.Storage;
import bebop.ui.Ui;

/**
 * Deletes tasks from the tasklist.
 */
public class DeleteCommand extends Command {
    private final String command;

    /**
     * DeleteCommand Constructor.
     *
     * @param command command being deleted.
     */
    public DeleteCommand(String command) {
        this.command = command;
    }

    /**
     * Deletes the command from TaskList.
     *
     * @param tasks Tasklist storing tasks.
     * @param ui Ui to print commands.
     * @param storage stores task into Bebop.txt.
     *
     * @return string if the program will continue or not.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] input = this.command.split(" ");
            String output = "";
            assert input.length > 0;
            if (input.length == 1 || input.length > 2 || !isInteger(input[1])
                    || Integer.parseInt(input[1]) > (tasks.size())) {
                throw new BebopException("Delete only accepts valid integers");
            }
            int taskNum = Integer.parseInt(input[1]);
            output = "Alright! Congrats on finishing your task:)\n\t"
                    + tasks.getTask(taskNum - 1).printTask();
            tasks.deleteTask(taskNum - 1);
            return output;
        } catch (BebopException b) {
            return b.getMessage();
        }
    }

}
