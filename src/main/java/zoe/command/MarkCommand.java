package zoe.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import zoe.ZoeException;
import zoe.storage.Storage;
import zoe.task.Task;
import zoe.task.TaskList;
import zoe.ui.Ui;

/**
 * Represents a command to mark a task as completed in the task list.
 * This command retrieves the task from the specified index, marks it as done,
 * saves the updated task list to storage, and displays the updated task via the {@link Ui}.
 */
@Getter
@AllArgsConstructor
public class MarkCommand implements Command {
    public static final String COMMAND_WORD = "mark";
    private final int index;

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws ZoeException {
        if (!(0 <= index && index < taskList.size())) {
            throw new ZoeException("Incorrect index");
        }
        Task task = taskList.get(index);
        task.markAsDone();
        storage.save(taskList);
        ui.showMarkedTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
