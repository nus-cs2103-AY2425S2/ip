package runny.commands;

import runny.RunnyException;
import runny.storage.Storage;
import runny.task.Event;
import runny.task.Task;
import runny.task.TaskList;
import runny.ui.Ui;

/**
 * Adds a new Event task to the task list.
 */
public class EventCommand implements Command {
    private String details;
    private int indexTask;

    /**
     * Creates an EventCommand with the specified details.
     *
     * @param details The details of the event task.
     */
    public EventCommand(String details) {
        this.details = details;
        this.indexTask = 0;
    }


    /**
     * Executes command by creating and adding an Event task to the task list.
     * Displays relevant messages to the user, saves the task to the local file.
     *
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving task data after modification.
     * @param tasks   The list of tasks.
     * @throws RunnyException If the event details are empty.
     */
    @Override
    public void doCommand(Ui ui, Storage storage, TaskList tasks) throws RunnyException {
        assert ui != null && storage != null && tasks != null : "One of the three objects, "
                + "ui,storage or tasks is null";
        if (details == "") {
            throw new RunnyException("OOPS!!! The description of an event cannot be empty.\n");
        }
        if (!details.contains("/from") || !details.contains("/to")) {
            throw new RunnyException("OOPS!!! The format for your given command is wrong.\n"
                    + "Please use the following format:\nevent <eventName> /from YYYY-MM-DD HHMM "
                    + "/to YYYY-MM-DD HHMM");
        }
        String[] eventFront = details.split("/from");
        String[] eventBack = eventFront[1].split(("/to"));
        Task currentTask = new Event(eventFront[0], eventBack[0].trim(), eventBack[1].trim());
        tasks.add(currentTask);
        this.indexTask = tasks.size();
        storage.writeToFile(tasks);
        ui.printMessage("Got it. I've added this task:\n" + currentTask.toString()
                + "\nNow you have " + Integer.toString(tasks.size()) + " tasks in the list.");

    }

    /**
     * Loads the event task from the command details and adds it to the task list.
     *
     * @param tasks The list of tasks to which the new task will be added.
     */
    @Override
    public void loadTask(TaskList tasks) throws RunnyException {
        String[] eventFront = details.split("/from");
        String[] eventBack = eventFront[1].split(("/to"));
        Task currentTask = new Event(eventFront[0], eventBack[0].trim(), eventBack[1].trim());
        tasks.add(currentTask);
    }

    /**
     * Undoes the EventCommand and removes the task from the task list.
     *
     * @param tasks The list of tasks to which the task will be deleted.
     * @throws RunnyException If an error occurs during command execution.
     */
    @Override
    public Command undoTask(TaskList tasks) throws RunnyException {
        return new DeleteCommand(Integer.toString(this.indexTask));
    }


}
