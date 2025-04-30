package yuki.command;

import yuki.Storage;
import yuki.TaskList;
import yuki.Ui;
import yuki.YukiException;
import yuki.task.Task;

public class UnmarkCommand extends Command {
    int taskNumber = getValidatedTaskNumber();
    public UnmarkCommand(String[] commands, String description, boolean isExit) {
        super(commands, description, isExit);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command to unmark a task from the task list.
     * @param tasks TaskList containing the tasks.
     * @param ui Ui object to interact with the user.
     * @param storage Storage object to save the tasks.
     * @return The output of the command.
     * @throws YukiException if the task number does not exist or is not a number.
     */
    @Override
    public String execute(TaskList<Task> tasks, Ui ui, Storage storage) throws YukiException {
        return handleUnmarkCommand(tasks);
    }

    private String handleUnmarkCommand(TaskList<Task> tasks) {
        int taskNumber = getValidatedTaskNumber();
        if (taskNumber >= tasks.size()) return "Task number does not exist";
        if (taskNumber == -1) return "Task number must be a positive number";
        tasks.get(taskNumber).markAsNotDone();
        StringBuilder output = new StringBuilder("Nice! I've unmarked this task:\n");
        output.append(tasks.getDescription(taskNumber)).append("\n");
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
     * Undo the command.
     * @param tasks TaskList containing the tasks.
     * @return The output of the command.
     */
    @Override
    public String undo(TaskList<Task> tasks) {
        tasks.get(taskNumber).markAsDone();
        StringBuilder output = new StringBuilder("Nice! I've marked this task as done:\n");
        output.append(tasks.getDescription(taskNumber)).append("\n");
        Command.lastCommand = this;
        Storage.save(tasks);
        return output.toString();
    }
}
