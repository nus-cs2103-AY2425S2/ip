package zoe.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import zoe.ZoeException;
import zoe.storage.Storage;
import zoe.task.Task;
import zoe.task.TaskList;
import zoe.task.Todo;
import zoe.ui.Ui;

/**
 * Represents a command to create a Todo task.
 * This command adds a new Todo task to the task list, saves it in storage,
 * and displays the added task to the user via the UI.
 */
@Getter
@AllArgsConstructor
public class TodoCommand implements Command {
    public static final String COMMAND_WORD = "todo";
    public static final String ALIAS = "t";
    private final String taskName;

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws ZoeException {
        Task task = new Todo(taskName);
        taskList.add(task);
        storage.save(taskList);
        ui.showAddedTask(taskList, task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
