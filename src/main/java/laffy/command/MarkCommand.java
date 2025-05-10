package laffy.command;

import laffy.Storage;
import laffy.Ui;
import laffy.command.exceptions.BlankArgument;
import laffy.command.exceptions.InvalidIndex;
import laffy.tasklist.TaskList;
import laffy.tasklist.exceptions.TaskListException;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends IndexedCommand {
    private static final CommandWord COMMAND_WORD = CommandWord.MARK;

    /**
     * Constructor for MarkCommand.
     *
     * @param args The arguments to be parsed.
     * @throws InvalidIndex If the index is invalid.
     * @throws BlankArgument If the index is empty.
     */
    public MarkCommand(String args) throws InvalidIndex, BlankArgument {
        super(args);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws TaskListException {
        String output = taskList.markAsDone(super.getIndex());
        super.execute(taskList, storage);
        return output;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TaskListException {
        ui.echo(this.execute(taskList, storage));
    }

    public static String getDescription() {
        return getCommandWord() + " <index>";
    }

    public String getUsage() {
        return super.getUsage() + getDescription();
    }

    public static String getCommandWord() {
        return COMMAND_WORD.toString();
    }
}
