package laffy.command;

import laffy.Storage;
import laffy.Ui;
import laffy.command.exceptions.BlankArgument;
import laffy.tasklist.TaskList;
import laffy.tasklist.exceptions.TaskListException;

/**
 * Represents a command to find tasks.
 */
public class FindCommand extends Command {
    private static final CommandWord COMMAND_WORD = CommandWord.FIND;

    private final String toFind;

    /**
     * Constructor for FindCommand.
     *
     * @param args The arguments to be parsed.
     */
    public FindCommand(String args) throws BlankArgument {
        super(args);
        if (args.isEmpty() || args.isBlank()) {
            this.setValid(false);
            throw new BlankArgument("Search keyword cannot be empty.\n" + getUsage());
        }
        this.toFind = args.trim();
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws TaskListException {
        String output = taskList.find(this.toFind);
        super.execute(taskList, storage);
        return output;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TaskListException {
        ui.echo(this.execute(taskList, storage));
    }

    public static String getDescription() {
        return getCommandWord() + " <keyword>";
    }

    public String getUsage() {
        return super.getUsage() + getDescription();
    }

    public static String getCommandWord() {
        return COMMAND_WORD.toString();
    }
}
