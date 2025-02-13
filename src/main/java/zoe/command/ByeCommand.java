package zoe.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import zoe.storage.Storage;
import zoe.task.TaskList;
import zoe.ui.Ui;

/**
 * Represents a command to terminate the program.
 * The {@code ByeCommand} is executed when the user inputs the command "bye".
 * It displays a goodbye message and signals the application to exit.
 */
@Getter
@NoArgsConstructor
public class ByeCommand implements Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {}

    @Override
    public boolean isExit() {
        return true;
    }
}
