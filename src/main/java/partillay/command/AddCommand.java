package partillay.command;

import partillay.task.Task;
import partillay.task.TaskList;
import partillay.ui.Ui;

/**
 * Represents a command that adds a task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs a new Add command with the specific task.
     *
     * @param task the task to be added to task list
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the task list and displays the updated task list.
     *
     * @param tasks the task list that stores current tasks
     * @param ui    the user interface for displaying output
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        int numTasksBefore = tasks.size();
        String result = "Got it. I've added this task:\n";
        result += task.toString() + "\n";
        tasks.addTask(task);
        int numTasksAfter = tasks.size();
        assert numTasksBefore == numTasksAfter - 1 : "Number of tasks should have incremented by 1";
        result += "Now you have " + tasks.size() + " tasks in the list.";
        return ui.getLinedMessage(result);
    }

    /**
     * Returns equality between the current instance and another object.
     *
     * @param other the object to be compared
     * @return {@code true} if both {@code AddCommand} instances have the same task to be added, otherwise {@code false}
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof AddCommand otherCommand) {
            return task.equals(otherCommand.task);
        }
        return false;
    }
}
