package laffy.command;

import laffy.Storage;
import laffy.Ui;
import laffy.command.exceptions.BlankArgument;
import laffy.command.exceptions.InvalidIndex;
import laffy.tasklist.TaskList;
import laffy.tasklist.exceptions.TaskListException;

/**
 * Represents a command that requires an index to be executed
 * and is a subclass of Command.
 */
public class IndexedCommand extends Command {
    private static final String COMMAND_WORD = "indexed";
    private final int index;

    /**
     * Constructor for IndexedCommand. This constructor is called by child classes.
     *
     * @param args The arguments to be parsed.
     * @throws InvalidIndex If the index is invalid.
     * @throws BlankArgument If the index is empty.
     */
    public IndexedCommand(String args) throws InvalidIndex, BlankArgument {
        super(args);
        if (args.isEmpty()) {
            this.index = -1;
            this.setValid(false);
            throw new BlankArgument("Index cannot be empty.\n" + this.getUsage());
        }
        try {
            Integer.parseInt(args.trim());
        } catch (NumberFormatException e) {
            this.index = -1;
            this.setValid(false);
            throw new InvalidIndex("Expected an integer but got " + args);
        }
        this.setValid(true);
        this.index = Integer.parseInt(args.trim()) - 1;
    }


    /**
     * Executes the command. This method is overridden by the child classes.
     *
     * @param taskList The task list to be modified.
     * @param storage The storage to save the task list.
     * @throws TaskListException If there is an error in the task list.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws TaskListException {
        super.execute(taskList, storage);
        return "";
    }

    /**
     * Executes the command. This method is overridden by the child classes.
     *
     * @param taskList The task list to be modified.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the task list.
     * @throws TaskListException If there is an error in the task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TaskListException {
        super.execute(taskList, ui, storage);
    }

    public int getIndex() {
        return this.index;
    }

    public static String getCommandWord() {
        return COMMAND_WORD;
    }
}
