package nikingoda.Command;

import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

import java.time.format.DateTimeParseException;

public class UpdateBeginCommand extends UpdateCommand {
    protected final String newBegin;

    /**
     * update begin_time of task with id
     *
     * @param id       id of task
     * @param newBegin new begin_time
     */

    public UpdateBeginCommand(int id, String newBegin) {
        super(id);
        this.newBegin = newBegin;
    }

    /**
     * @param tasks   TaskList
     * @param ui      Ui
     * @param storage Storage
     * @throws NikingodaException handle syntax valid or if task is not event
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NikingodaException {
        try {
            int id = this.getId() - 1; //change from 1-indexed to 0-indexed
//            ui.updateTask(tasks.updateTaskBegin(id, newBegin));
            String response = "Noted. I've updated this task: \n" + tasks.updateTaskBegin(id, newBegin);
            this.setResponse(response);
        } catch (NikingodaException e) {
            throw e;
        } catch (DateTimeParseException e) {
            throw new NikingodaException("Begin time should be in form: 'HHmm dd/mm/yyyy'");
        }
    }
}
