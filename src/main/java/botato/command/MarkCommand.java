package botato.command;

import botato.TaskList;
import botato.Ui;
import botato.exception.InvalidTaskNumberException;

/**
 * This class encapsulates the actions to be performed when a mark command is parsed by the parser.
 * When executed, it marks a task as complete based on the user's input string.
 */
public class MarkCommand extends Command {
    private final int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        assert taskNumber > 0 : "Task number must be greater than 0.";
        assert taskNumber <= TaskList.size() : "Task number must be less than or equal to TaskList.size()";
        return tasks.setTaskStatus(taskNumber, true);
    }
}
