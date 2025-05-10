package command;

import java.util.ArrayList;

import exceptions.ElmachoException;
import task.Task;
import task.Tasklist;
import ui.Ui;

public class ArchiveCommand extends Command{

    private final int index;
    
    public ArchiveCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Tasklist tasklist, Tasklist archivedTasklist, Ui ui) throws ElmachoException{
        assert index >= 1: "Index must be a positive integer.";

        if (index <= 0 || index > tasklist.getNumberOfTasks()) {
            throw new ElmachoException("Invalid task number. Change it.");
        }

        ArrayList<Task> tasks = tasklist.getTasks();
        Task task = tasks.get(index - 1);
        assert task != null : "Task should not be null.";

        archivedTasklist.add(task);
        tasklist.delete(index);
        ui.printArchivedTask(tasklist, archivedTasklist, task);
    }
}
