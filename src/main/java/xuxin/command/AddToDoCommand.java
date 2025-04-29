package xuxin.command;

import xuxin.main.Statistics;
import xuxin.main.Storage;
import xuxin.main.TaskList;
import xuxin.main.Ui;
import xuxin.exception.DukeException;
import xuxin.task.Task;
import xuxin.task.Todo;

/**
 * AddToDo Command is a command to add a todo task into the tasklist.
 */
public class AddToDoCommand extends Command {
    private String description;

    public AddToDoCommand(String command) {
        assert command != null;
        this.description = command.substring(5).trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage,
                        Statistics stats) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Task task = new Todo(description);
        tasks.addTask(task);
        stats.addNotDone();
        ui.addSuccess(task);
        ui.showTaskCount(tasks.getSize());
        try {
            storage.saveTasks(tasks);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}