package prophet.command;

import prophet.gui.Ui;
import prophet.storage.Storage;

/**
 * The UnknownCommand class represents the command to handle unknown commands from the chatbot.
 */
public class UnknownCommand extends Command {
    private static final String MESSAGE = "Part of your description is missing. Usage is as such: \n"
            + "1. To add todo tasks, format your input as such: todo description. eg: todo finish homework\n"
            + "2. To add deadline tasks, format your input as such: \n"
                + "deadline description /by when (format: YYYY-MM-DDTHH:MM). "
                + "eg: deadline finish homework /by 2025-02-19T23:59\n"
            + "3. To add event tasks, format your input as such: \n"
                + "event description /from when /to when in the same format as above. "
                + "eg: event sports day /from 2025-01-10T00:00 /to 2025-01-11T23:59\n"
            + "4. To mark, unmark and delete tasks, format your input as such: \n"
                + "(un)mark/delete task number. eg: mark 1 OR unmark 2 OR delete 3\n"
            + "5. To see your tasks, just type \"list\""
            + "6. To find tasks by name (or a segment of it), format your input as such: \n"
                + "find task name. eg: find work OR find homework\n"
            + "7. To see your schedule for a specific date, format your input as such: \n"
                + "schedule date. eg: schedule 2025-02-01";

    /**
     * Initialises a newly created UnknownCommand object.
     * @param type the type of command
     */
    public UnknownCommand(CommandType type) {
        super(type);
    }

    /**
     * Executes the command to handle unknown commands.
     * Prints out the error message.
     * @param ui the user interface of the chatbot
     * @param storage the storage of tasks accumulated as the chatbot runs
    * @return the message to be printed
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        return ui.print(UnknownCommand.MESSAGE + "\n");
    }
}
