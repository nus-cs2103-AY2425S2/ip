package zoe.command;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import zoe.ZoeException;
import zoe.storage.Storage;
import zoe.task.Event;
import zoe.task.Task;
import zoe.task.TaskList;
import zoe.ui.Ui;

/**
 * Represents a command to add an event task to the task list.
 * This command is responsible for creating an {@link Event} task with a name, start date, and end date.
 * After creating the task, it adds it to the {@link TaskList}, saves the updated task list to storage,
 * and displays a message via the {@link Ui}.
 */
@Getter
@AllArgsConstructor
public class EventCommand implements Command {
    public static final String COMMAND_WORD = "event";
    public static final String ALIAS = "e";
    private final String taskName;
    private final LocalDate from;
    private final LocalDate to;

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws ZoeException {
        Task task = new Event(taskName, from, to);
        taskList.add(task);
        storage.save(taskList);
        ui.showAddedTask(taskList, task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
