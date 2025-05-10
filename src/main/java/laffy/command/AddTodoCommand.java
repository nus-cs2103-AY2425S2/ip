package laffy.command;

import laffy.Storage;
import laffy.Ui;
import laffy.command.exceptions.BlankArgument;
import laffy.tasklist.TaskList;
import laffy.tasklist.exceptions.TaskListException;

/**
 * Represents a command to add a todo task.
 */
public class AddTodoCommand extends Command {
    private static final CommandWord COMMAND_WORD = CommandWord.TODO;
    private final String desc;

    /**
     * Constructor for AddTodoCommand.
     *
     * @param args The arguments to be parsed.
     * @throws BlankArgument If the description is empty.
     */
    public AddTodoCommand(String args) throws BlankArgument {
        super(args);
        if (args.isEmpty() || args.isBlank()) {
            this.setValid(false);
            throw new BlankArgument("Description cannot be empty.\n" + getUsage());
        } else {
            this.setValid(false);
        }
        this.desc = args.trim();
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws TaskListException {
        String output = "Got it. I've added this task:\n  "
                + taskList.addTodo(desc)
                + "\nNow you have " + taskList.size() + " tasks in the list.";
        super.execute(taskList, storage);
        return output;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TaskListException {
        ui.echo(this.execute(taskList, storage));
    }

    public static String getDescription() {
        return getCommandWord() + " <description>";
    }

    public String getUsage() {
        return super.getUsage() + getDescription();
    }

    public static String getCommandWord() {
        return COMMAND_WORD.toString();
    }
}
