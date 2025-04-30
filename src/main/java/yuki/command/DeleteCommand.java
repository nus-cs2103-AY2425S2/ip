package yuki.command;

import yuki.Storage;
import yuki.TaskList;
import yuki.Ui;
import yuki.YukiException;
import yuki.task.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    Task deletedTask = null;

    public DeleteCommand(String[] commands, String description, boolean isExit) {
        super(commands, description, isExit);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command to delete a task from the task list.
     * @param tasks TaskList containing the tasks.
     * @param ui Ui object to interact with the user.
     * @param storage Storage object to save the tasks.
     * @return The output of the command.
     * @throws YukiException if the task number does not exist or is not a number.
     */
    @Override
    public String execute(TaskList<Task> tasks, Ui ui, Storage storage) throws YukiException {
        return handleDeleteCommand(tasks);
    }

    private String handleDeleteCommand(TaskList<Task> tasks) {
        int taskNumber = getValidatedTaskNumber();
        if (taskNumber >= tasks.size()) return "Task number does not exist";

        StringBuilder output = new StringBuilder();
        output.append(tasks.getDescription(taskNumber)).append("\n");
        this.deletedTask = tasks.remove(taskNumber);
        output.append("Noted. I've removed this task:\n");
        output.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
        Command.lastCommand = this;
        Storage.save(tasks);
        return output.toString();
    }

    private int getValidatedTaskNumber() {
        try {
            int taskNumber = Integer.parseInt(getCommand(1)) - 1;
            if (taskNumber < 0) throw new NumberFormatException();
            return taskNumber;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Undoes the deletion of the task.
     * @param tasks TaskList containing the tasks.
     * @return The output of the undo command.
     * @throws YukiException if no task has been deleted yet.
     */
    @Override
    public String undo(TaskList<Task> tasks) throws YukiException {
        if (deletedTask == null) {
            throw new YukiException("No task has been deleted yet.");
        }
        tasks.add(deletedTask);
        Storage.save(tasks);
        return "Task has been added back to the list.";
    }
}