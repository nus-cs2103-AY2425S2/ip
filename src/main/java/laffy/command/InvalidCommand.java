package laffy.command;

import laffy.Storage;
import laffy.Ui;
import laffy.tasklist.TaskList;

/**
 * Represents a command that is invalid.
 */
public class InvalidCommand extends Command {
    public static final CommandWord COMMAND_WORD = CommandWord.INVALID;

    /**
     * Constructor for InvalidCommand.
     * Always valid, as it is a catch-all command.
     *
     * @param args The arguments to be parsed.
     */
    public InvalidCommand(String args) {
        super(args);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        StringBuilder sb = new StringBuilder();
        sb.append(InvalidCommand.getDescription());
        sb.append("\n");
        sb.append("Here are the available commands:");
        sb.append("\n1. ");
        sb.append(ListCommand.getDescription());
        sb.append("\n2. ");
        sb.append(AddTodoCommand.getDescription());
        sb.append("\n3. ");
        sb.append(AddDeadlineCommand.getDescription());
        sb.append("\n4. ");
        sb.append(AddEventCommand.getDescription());
        sb.append("\n5. ");
        sb.append(FindCommand.getDescription());
        sb.append("\n6. ");
        sb.append(MarkCommand.getDescription());
        sb.append("\n7. ");
        sb.append(UnmarkCommand.getDescription());
        sb.append("\n8. ");
        sb.append(DeleteCommand.getDescription());
        sb.append("\n9. ");
        sb.append(ExitCommand.getDescription());
        return sb.toString();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.echo(this.execute(taskList, storage));
    }

    public static String getDescription() {
        return "Invalid command. Please enter a valid command.";
    }

    public String getUsage() {
        return "";
    }

    public static String getCommandWord() {
        return COMMAND_WORD.toString();
    }
}
