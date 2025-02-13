package zoe.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import zoe.storage.Storage;
import zoe.task.TaskList;
import zoe.ui.Ui;

/**
 * Represents a command that is considered invalid or unrecognized.
 * <p>
 * This command is executed when the user enters input that does not correspond
 * to any valid command.
 * </p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IncorrectCommand implements Command {
    private String errorMessage = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    /**
     * Executes the command by displaying the predefined error message to the user.
     *
     * @param storage  The storage object to handle data persistence (unused in this command).
     * @param taskList The current list of tasks (unused in this command).
     * @param ui       The UI object to communicate with the user.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.reply(errorMessage);
    }

    /**
     * Indicates whether this command will terminate the application.
     *
     * @return {@code false}, as the application should not exit after an incorrect command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
