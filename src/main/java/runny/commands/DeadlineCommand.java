package runny.commands;

import runny.RunnyException;
import runny.storage.Storage;
import runny.task.Deadline;
import runny.task.Task;
import runny.task.TaskList;
import runny.ui.Ui;

/**
 * Adds a new Deadline task to the task list.
 */
public class DeadlineCommand implements Command {
    private int indexTask;
    private String details;

    /**
     * Creates a DeadlineCommand with the specified details.
     *
     * @param details The details of the deadline task.
     */
    public DeadlineCommand(String details) {
        this.details = details;
        this.indexTask = 0;
    }

    /**
     * Executes command by creating and adding a Deadline task to the task list.
     * Displays relevant messages to the user, saves the task to the local file.
     *
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving task data after modification.
     * @param tasks   The list of tasks.
     * @throws RunnyException If the deadline details are empty.
     */
    @Override
    public void doCommand(Ui ui, Storage storage, TaskList tasks) throws RunnyException {
        assert ui != null && storage != null && tasks != null : "One of the three objects, "
                + "ui,storage or tasks is null";
        if (details == "") {
            throw new RunnyException("OOPS!!! The description of a deadline cannot be empty.\n");
        }
        if (!details.contains("/by")) {
            throw new RunnyException("OOPS!!! The format for your given command is wrong.\n"
                    + "Please use the following format: deadline <name> /by YYYY-MM-DD HHMM");
        }
        String[] deadlineFront = details.split("/by");
        Task currentTask = new Deadline(deadlineFront[0], deadlineFront[1].trim());
        tasks.add(currentTask);
        this.indexTask = tasks.size();
        storage.writeToFile(tasks);
        ui.printMessage("Got it. I've added this task:\n" + currentTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Loads the task from the command details and adds it to the task list.
     *
     * @param tasks The list of tasks to which the new task will be added.
     */
    @Override
    public void loadTask(TaskList tasks) {
        String[] partDeadline = details.split("/by");
        Task curr = new Deadline(partDeadline[0], partDeadline[1].trim());
        tasks.add(curr);
    }

    /**
     * Undoes the DeadlineCommand and removes the tasks from the task list.
     *
     * @param tasks The list of tasks to which the task will be deleted.
     * @return The command to be executed.
     * @throws RunnyException If an error occurs during command execution.
     */
    @Override
    public Command undoTask(TaskList tasks) throws RunnyException {
        return new DeleteCommand(Integer.toString(this.indexTask));
    }

}
