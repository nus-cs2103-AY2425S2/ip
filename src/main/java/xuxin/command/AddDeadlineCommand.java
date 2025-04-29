package xuxin.command;

import xuxin.main.Statistics;
import xuxin.main.Storage;
import xuxin.main.TaskList;
import xuxin.main.Ui;
import xuxin.exception.DukeException;
import xuxin.task.Task;
import xuxin.task.Deadline;

/**
 * AddToDo Command is a command to add a deadline task into the tasklist.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private String date;

    public AddDeadlineCommand(String command) throws DukeException {
        assert command.length() > 0;
        if (!command.contains(" /by "))
            throw new DukeException("A deadline must have '/by '.");
        String[] parts = command.substring(9).split(" /by ");
        if (parts.length < 2) throw new DukeException("Incomplete deadline format.");
        this.description = parts[0].trim();
        this.date = parts[1].trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage,
                        Statistics stats) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        Task task = new Deadline(description, date);
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