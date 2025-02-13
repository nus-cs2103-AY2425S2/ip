package zoe.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import zoe.ZoeException;
import zoe.storage.Storage;
import zoe.task.Task;
import zoe.task.TaskList;
import zoe.ui.Ui;

/**
 * Represents a command to delete a task at a specified index from the task list.
 * The command removes the task, updates the storage, and displays the removed task
 * information to the user.
 */
@Getter
@AllArgsConstructor
public class DeleteCommand implements Command {
    public static final String COMMAND_WORD = "delete";
    private final int index;

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws ZoeException {
        if (!(0 <= index && index < taskList.size())) {
            throw new ZoeException("Incorrect index");
        }
        Task task = taskList.remove(index);
        storage.save(taskList);
        ui.showRemovedTask(taskList, task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
