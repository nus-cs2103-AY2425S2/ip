package laffy.command;

import laffy.Storage;
import laffy.Ui;
import laffy.tasklist.TaskList;
import laffy.tasklist.exceptions.TaskListException;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    private static final CommandWord COMMAND_WORD = CommandWord.EXIT;

    /**
     * Constructor for ExitCommand.
     *
     * @param args The arguments to be parsed.
     */
    public ExitCommand(String args) {
        super(args);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws TaskListException {
        super.execute(taskList, storage);
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TaskListException {
        ui.echo(this.execute(taskList, storage));
    }

    @Override
    public boolean isExit() {
        return true;
    }

    public static String getDescription() {
        return getCommandWord();
    }

    public String getUsage() {
        return "Exits Application";
    }

    public static String getCommandWord() {
        return COMMAND_WORD.toString();
    }

}
