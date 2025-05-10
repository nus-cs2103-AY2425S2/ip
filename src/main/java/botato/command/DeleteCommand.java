package botato.command;

import botato.TaskList;
import botato.Ui;
import botato.exception.InvalidTaskNumberException;
import botato.task.Task;

/**
 * This class encapsulates the actions to be performed when {@link botato.Parser} parses a delete command from the
 * user's input command string. When executed, it deletes a task from the given TaskList based on the index in the user
 * input string.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    @Override
    public String execute(TaskList tasks, Ui ui) {
        assert taskNumber > 0 : "Task number must be greater than 0.";
        assert taskNumber <= TaskList.size() : "Task number must be less than or equal to TaskList.size()";
        Task removedTask = tasks.deleteTask(taskNumber);
        System.out.println("Task has been successfully removed:\n" + removedTask);
        return "Task has been successfully removed:\n" + removedTask;
    }
}
