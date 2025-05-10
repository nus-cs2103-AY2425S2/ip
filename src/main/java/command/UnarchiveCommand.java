package command;

import java.util.ArrayList;

import exceptions.ElmachoException;
import task.Task;
import task.Tasklist;
import ui.Ui;

public class UnarchiveCommand extends Command {

    private final int index;

    public UnarchiveCommand(int index) {
        this.index = index;
    }

    public void execute(Tasklist tasklist, Tasklist archivedTasklist, Ui ui) throws ElmachoException {
        assert index >= 1 : "Index must be a positive integer.";

        if (index <= 0 || index > archivedTasklist.getNumberOfTasks()) {
            throw new ElmachoException("Invalid task number. Change it.");
        }

        ArrayList<Task> tasks = archivedTasklist.getTasks();
        Task task = tasks.get(index - 1);
        assert task != null : "Task should not be null.";

        tasklist.add(task);
        archivedTasklist.delete(index);
        ui.printUnarchivedTask(tasklist, task);
    }
}
