package Tom.commands;

import Tom.TomException;
import Tom.Storage;
import Tom.tasks.Task;
import Tom.tasks.TaskList;

/**
 * Handles marking tasks.
 */
public class MarkCommand extends Command {
    private int taskIndex;
    private boolean isMarkingDone;

    /**
     * Constructs a MarkCommand with the given input and task type.
     *
     * @param input The array of input strings containing task details.
     * @param isMarkingDone Whether marking is done or not
     */
    public MarkCommand(String[] input, boolean isMarkingDone) throws TomException {
        try {
            int number = Integer.parseInt(input[1]);
            taskIndex = number - 1;
        } catch (NumberFormatException e) {
            throw new TomException("Invalid command! Use 'mark <task number>'.");
        }
        if (input.length < 2) {
            throw new TomException("Invalid command! Use 'mark <task number>'.");
        }

        this.isMarkingDone = isMarkingDone;
    }

    /**
     * Executes the mark or unmark task command.
     *
     * @param taskList The TaskList instance.
     * @return The string representation of the command's response.
     * @throws TomException If the task index is invalid.
     */
    @Override
    public String execute(TaskList taskList) throws TomException {
        if (taskIndex >= taskList.getTaskListSize() || taskIndex < 0) {
            throw new TomException("Invalid task number! Use a valid number.");
        }
        Task task = taskList.markTask(taskIndex, isMarkingDone);
        Storage.saveTasks(taskList.getTaskList());

        return "Well done, this task is completed!\n   " + task;
    }
}