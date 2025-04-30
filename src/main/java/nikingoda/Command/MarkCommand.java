package nikingoda.Command;

import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

public class MarkCommand extends Command {
    private final int id;

    /**
     * command to mark task with id
     *
     * @param id id
     */
    public MarkCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NikingodaException {
        try {
            // ui.mark(tasks, id - 1);             //Id must be transform to 0-indexed
            String response = "Nice! I've marked this task as done:\n" + tasks.mark(id - 1); //Id must be transform to 0-indexed
            this.setResponse(response);
            storage.saveTask(tasks);
        } catch (NikingodaException e) {
            throw e;
        } catch (IndexOutOfBoundsException e) {
            throw new NikingodaException(" There's no task with your id");
        }
    }
}
