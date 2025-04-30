package nikingoda.Command;

import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.Task.Task;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NikingodaException {
//        ui.list(tasks);
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        ArrayList<Task> tmpTask = tasks.getTasks();
        int i = 1;
        for (Task task : tmpTask) {
            response.append(i).append(". ").append(task).append("\n");
            i++;
        }
        this.setResponse(response.toString());
    }
}
