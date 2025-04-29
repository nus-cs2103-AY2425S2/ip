package xuxin.command;

import xuxin.main.Statistics;
import xuxin.main.Storage;
import xuxin.main.TaskList;
import xuxin.main.Ui;
import xuxin.exception.DukeException;
import xuxin.task.Event;
import xuxin.task.Task;

/**
 * AddEvent Command is a command to add an event task into the tasklist.
 */
public class AddEventCommand extends Command {
    private String description;
    private String start;
    private String end;

    public AddEventCommand(String command) throws DukeException {
        assert command.length() > 0;
        if (!command.contains(" /from ") || !command.contains(" /to ")) throw new DukeException("An event must have '/from ' and '/to '.");
        String[] parts = command.substring(6).split(" /from ");
        String[] timeParts = parts[1].split(" /to ");
        this.description = parts[0];
        this.start = timeParts[0];
        this.end = timeParts[1];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage,
                        Statistics stats) throws DukeException {
        Task event = new Event(description, start, end);
        tasks.addTask(event);
        stats.addNotDone();
        ui.addSuccess(event);
        ui.showTaskCount(tasks.getSize());
        storage.saveTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}