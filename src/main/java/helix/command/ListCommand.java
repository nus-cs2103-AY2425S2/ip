package helix.command;

import java.util.List;

import helix.enums.CommandType;
import helix.task.Task;
import helix.task.TaskList;
import helix.ui.ConsoleUi;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {
        super(CommandType.LIST);
    }

    /**
     * Executes the list helix.command, printing all tasks in the TaskList.
     *
     * @param taskList the TaskList containing tasks to be listed
     * @param consoleUi the ConsoleUi component used to display messages to the user
     */
    @Override
    public void execute(TaskList taskList, ConsoleUi consoleUi) {
        // get unmodifiable list of tasks and pass it to ConsoleUi
        List<Task> tasks = taskList.getTasks();
        consoleUi.showTaskList(tasks);
    }
}
