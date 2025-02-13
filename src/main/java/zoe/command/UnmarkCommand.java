package zoe.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import zoe.ZoeException;
import zoe.storage.Storage;
import zoe.task.Task;
import zoe.task.TaskList;
import zoe.ui.Ui;

/**
 * Represents a command to unmark a task as undone in the task list.
 * <p>
 * Executes the operation of marking a task as undone, updating the storage,
 * and displaying the updated task status via the UI.
 */
@Getter
@AllArgsConstructor
public class UnmarkCommand implements Command {
    public static final String COMMAND_WORD = "unmark";
    private final int index;

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws ZoeException {
        if (!(0 <= index && index < taskList.size())) {
            throw new ZoeException("Incorrect index");
        }
        Task task = taskList.get(index);
        task.markAsUndone();
        storage.save(taskList);
        ui.showUnmarkedTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
