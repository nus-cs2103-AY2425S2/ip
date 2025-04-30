package nikingoda.Command;

import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

public class UpdateDescriptionCommand extends UpdateCommand {
    protected final String newDescription;

    /**
     * update description of task
     *
     * @param id             id
     * @param newDescription new description
     */

    public UpdateDescriptionCommand(int id, String newDescription) {
        super(id);
        this.newDescription = newDescription;
    }

    /**
     * @param tasks   TaskList
     * @param ui      Ui
     * @param storage Storage
     * @throws NikingodaException handle syntax invalid
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NikingodaException {
        int id = this.getId() - 1; //change from 1-indexed to 0-indexed
        if (newDescription.trim().isBlank()) {
            throw new NikingodaException("New description cannot be blank");
        }
//        ui.updateTask(tasks.updateTaskDescription(id, newDescription.trim()));
        String response = "Noted. I've updated this task: \n" + tasks.updateTaskDescription(id, newDescription);
        storage.saveTask(tasks);
        this.setResponse(response);
    }
}
