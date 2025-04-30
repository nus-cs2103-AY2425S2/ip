package nikingoda.Command;

import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

import java.time.format.DateTimeParseException;

public class UpdateEndCommand extends UpdateCommand {
    protected final String newEnd;

    /**
     * update end_time for task with id
     *
     * @param id     id of task
     * @param newEnd new end_time
     */
    public UpdateEndCommand(int id, String newEnd) {
        super(id);
        this.newEnd = newEnd;
    }

    /**
     * update end_time for task
     *
     * @param tasks   TaskList
     * @param ui      Ui
     * @param storage Storage
     * @throws NikingodaException handle for syntax invalid or if the task is not event
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NikingodaException {
        try {
            int id = this.getId() - 1; //change form 1-indexed to 0-indexed
//            ui.updateTask(tasks.updateTaskEnd(id, newEnd));
            String response = "Noted. I've updated this task: \n" + tasks.updateTaskEnd(id, newEnd);
            storage.saveTask(tasks);
            this.setResponse(response);
        } catch (NikingodaException e) {
            throw e;
        } catch (DateTimeParseException e) {
            throw new NikingodaException(" End time should be in form: 'HHmm dd/mm/yyyy'");
        }
    }
}
