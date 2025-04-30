package nikingoda.Command;

import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

import java.time.format.DateTimeParseException;

public class UpdateDeadlineCommand extends UpdateCommand {
    protected final String newDeadline;

    /**
     * update deadline of task with id
     *
     * @param id          id of task
     * @param newDeadline new deadline
     */

    public UpdateDeadlineCommand(int id, String newDeadline) {
        super(id);
        this.newDeadline = newDeadline;
    }

    /**
     * @param tasks   TaskList
     * @param ui      Ui
     * @param storage Storage
     * @throws NikingodaException handle syntax invalid or if task is not deadline
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NikingodaException {
        try {
            int id = this.getId() - 1; //change from 1-indexed to 0-indexed
//            ui.updateTask(tasks.updateTaskDeadline(id, newDeadline));
            String response = "Noted. I've updated this task: \n" + tasks.updateTaskDeadline(id, newDeadline);
            storage.saveTask(tasks);
            this.setResponse(response);
        } catch (NikingodaException e) {
            throw e;
        } catch (DateTimeParseException e) {
            throw new NikingodaException(" Deadline should be in form: 'HHmm dd/mm/yyyy'");
        }
    }
}
