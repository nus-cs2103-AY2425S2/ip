package nikingoda.Command;

import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

public class ExitCommand extends Command {
    /**
     * Command to exit chatbot
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NikingodaException {
        try {
            storage.saveTask(tasks);
            this.setResponse("Bye. Hope to see you again soon!");
        } catch (NikingodaException e) {
            throw e;
        }
    }

    /**
     * isExit() of exit command need to return true as we exit the program
     *
     * @return true as this is exit command
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
