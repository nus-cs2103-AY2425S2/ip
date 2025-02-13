package zoe.command;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import zoe.ZoeException;
import zoe.storage.Storage;
import zoe.task.Deadline;
import zoe.task.Task;
import zoe.task.TaskList;
import zoe.ui.Ui;

/**
 * Represents a command to add a deadline task.
 * Executes the addition of a {@link Deadline} to the task list and saves it to storage.
 * Displays feedback on the action performed.
 */
@Getter
@AllArgsConstructor
public class DeadlineCommand implements Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String ALIAS = "d";
    private final String taskName;
    private final LocalDate by;

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws ZoeException {
        Task task = new Deadline(taskName, by);
        taskList.add(task);
        storage.save(taskList);
        ui.showAddedTask(taskList, task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
