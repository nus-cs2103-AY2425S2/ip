package laffy.command;

import laffy.Storage;
import laffy.Ui;
import laffy.command.exceptions.BlankArgument;
import laffy.command.exceptions.InvalidDatetimeFormat;
import laffy.command.exceptions.MissingKeywordFlag;
import laffy.command.exceptions.TooManyArguments;
import laffy.tasklist.TaskDateProvider;
import laffy.tasklist.TaskList;
import laffy.tasklist.exceptions.TaskListException;

/**
 * Represents a command to add a deadline.
 */
public class AddDeadlineCommand extends Command {
    private static final CommandWord COMMAND_WORD = CommandWord.DEADLINE;

    private final String desc;
    private final String by;

    /**
     * Constructor for AddDeadlineCommand.
     *
     * @param args The arguments to be parsed.
     * @throws BlankArgument If the description or deadline is empty.
     * @throws InvalidDatetimeFormat If the deadline is not in the correct format.
     * @throws MissingKeywordFlag If the keyword flag by is not present in the command.
     * @throws TooManyArguments If there are too many arguments in the command.
     */
    public AddDeadlineCommand(String args)
            throws BlankArgument, InvalidDatetimeFormat, MissingKeywordFlag, TooManyArguments {
        super(args);
        super.checkKeywordFlagIsPresent(args, "/by");
        String[] arr = args.split(" /by ");
        if (arr.length < 2) {
            this.setValid(false);
            throw new BlankArgument("Description and deadline cannot be empty.\n" + getUsage());
        } else if (arr.length > 2) {
            this.setValid(false);
            throw new TooManyArguments("Expected one \"/by\" but got many instead.\n" + getUsage());
        } else {
            this.desc = arr[0].trim();
            this.by = arr[1].trim();
            this.setValid(!this.desc.isBlank() && !this.desc.isEmpty()
                    && !this.by.isBlank() && !this.by.isEmpty());
            if (!this.isValid()) {
                throw new BlankArgument("Description and deadline cannot be empty.\n" + getUsage());
            }
        }
        this.setValid(TaskDateProvider.isValidDateTime(this.by));
        if (!this.isValid()) {
            throw new InvalidDatetimeFormat(this.by);
        }
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws TaskListException {
        String output = "Got it. I've added this task:\n  "
                + taskList.addDeadline(desc, by)
                + "\nNow you have " + taskList.size() + " tasks in the list.";
        super.execute(taskList, storage);
        return output;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TaskListException {
        ui.echo(this.execute(taskList, storage));
    }

    public static String getDescription() {
        return getCommandWord() + " <description> /by <deadline: dd-MM-yy[ HHMM]>";
    }

    public String getUsage() {
        return super.getUsage() + getDescription();
    }

    public static String getCommandWord() {
        return COMMAND_WORD.toString();
    }
}
