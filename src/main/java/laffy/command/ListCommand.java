package laffy.command;

import laffy.Storage;
import laffy.Ui;
import laffy.tasklist.TaskList;
import laffy.tasklist.exceptions.TaskListException;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    private static final CommandWord COMMAND_WORD = CommandWord.LIST;

    /**
     * Constructor for ListCommand.
     *
     * @param args The arguments to be parsed.
     */
    public ListCommand(String args) {
        super(args);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws TaskListException {
        String output = taskList.toString();
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
        return COMMAND_WORD.toString();
    }
}
