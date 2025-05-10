package laffy.command;

import laffy.Storage;
import laffy.Ui;
import laffy.tasklist.TaskList;
import laffy.tasklist.exceptions.TaskListException;

/**
 * Represents a command to list upcoming tasks.
 */
public class UpcomingCommand extends Command {
    private static final CommandWord commandWord = CommandWord.UPCOMING;

    public UpcomingCommand(String args) {
        super(args);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws TaskListException {
        String output = taskList.getUpcomingTasks();
        super.execute(taskList, storage);
        return output;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TaskListException {
        ui.echo(this.execute(taskList, storage));
    }

    public static String getDescription() {
        return getCommandWord();
    }

    public String getUsage() {
        return super.getUsage() + getDescription();
    }

    public static String getCommandWord() {
        return commandWord.toString();
    }
}
