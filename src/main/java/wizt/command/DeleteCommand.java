package wizt.command;

import java.util.ArrayList;

import wizt.storage.Storage;
import wizt.task.Task;
import wizt.task.TaskList;
import wizt.ui.Ui;
import wizt.ui.WizTException;


/**
 *  Represents commands that adds deletes tasks from TasksList
 */

public class DeleteCommand extends Command {
    private String input;
    public DeleteCommand() {
        super();
    }
    public DeleteCommand(String input1) {
        this.input = input1;
    }

    /**
     * Delete the task from tasklist
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder response = new StringBuilder();
        try {
            String[] split = input.split(" ");
            if (split.length < 2) {
                throw new WizTException("Hmm, Please specify the task number to delete.");
            }
            int no = Integer.parseInt(split[1]);
            ArrayList<Task> tasklists = tasks.getTasksList();
            assert no > 0 && no <= tasklists.size() : "Error! Please Choose the appropriate number from the task list";
            if (!(no > 0 && no <= tasklists.size())) {
                throw new WizTException("Error! Please Choose the appropriate number from the task list");
            }
            response.append("\n Noted Boss! I've removed this task: \n")
                    .append(tasklists.get(no - 1).toString());
            tasklists.remove(no - 1);
            response.append("\n Now you have " + tasklists.size() + " in the list.");
        } catch (AssertionError e) {
            response.append("Error! Please Choose the appropriate number from the task list");
        } catch (WizTException e) {
            response.append(e.getMessage());
        }
        return response.toString();
    }
}
