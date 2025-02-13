package zoe.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import zoe.storage.Storage;
import zoe.task.TaskList;
import zoe.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * This command calls the {@link Ui} to display the current tasks stored in the {@link TaskList}.
 */
@Getter
@NoArgsConstructor
public class ListCommand implements Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.showList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
