package wizt.command;

import java.util.ArrayList;

import wizt.storage.Storage;
import wizt.task.Task;
import wizt.task.TaskList;
import wizt.ui.Ui;



/**
 *  Represents commands that list items in TasksList
 */
public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    /**
     * List all tasks in task list
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder response = new StringBuilder();
        ArrayList<Task> taskList = tasks.getTasksList();
        if (taskList.isEmpty()) {
            response.append("\n Hmm, You have no tasks in your list.");
        } else {
            response.append("\n Roger Boss! Here are the tasks in your list: ");
            for (int i = 0; i < taskList.size(); i++) {
                response.append("\n" + (i + 1) + ". " + taskList.get(i).toString());
            }
        }
        return response.toString();
    }
}
