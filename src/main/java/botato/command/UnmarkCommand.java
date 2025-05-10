package botato.command;

import botato.TaskList;
import botato.Ui;
import botato.exception.InvalidTaskNumberException;

/**
 * This class encapsulates the actions to be performed when an unmark command is parsed from the user input string.
 * When executed, it marks a command as incomplete based on the index from the user input string.
 */
public class UnmarkCommand extends Command {
    private int taskNumber;
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    @Override
    public String execute(TaskList tasks, Ui ui) {
        assert taskNumber > 0 : "Task number must be greater than 0.";
        assert taskNumber <= TaskList.size() : "Task number must be less than or equal to TaskList.size()";
        return tasks.setTaskStatus(taskNumber, false);
    }
}
