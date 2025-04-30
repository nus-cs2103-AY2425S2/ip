package nikingoda.Command;

import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

public class UnmarkCommand extends Command {
    private final int id;

    /**
     * command to unmark task with id
     *
     * @param id id
     */
    public UnmarkCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NikingodaException {
        try {
//            ui.unmark(tasks, id - 1);             //Id must be transform to 0-indexed
            String response = "OK, I've marked this task as not done yet:\n" + tasks.unmark(id - 1); //Id must be transform to 0-indexed
            this.setResponse(response);
            storage.saveTask(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new NikingodaException("There's no task with your id");
        }
    }
}
