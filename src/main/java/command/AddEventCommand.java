package command;

import core.TaskList;
import ui.Ui;
import storage.Storage;
import exception.BaimiException;
import task.Event;

/**
 * Represents a command to add a new event task to the task list.
 */
public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructs an AddEventCommand with a specified task description and event time.
     *
     * @param description The description of the event task to add.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the add event command, adding a new event task to the task list.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @return The response to the user command.
     * @throws BaimiException If an error occurs during the execution of the command.
     */
    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) throws Exception {
        Event event = new Event(description, from, to);
        tasks.addTasks(event);
        storage.save(tasks.getTasks());

        return "Got it baby. I've added this event:\n  " + event +
                "\nNow you have " + tasks.getTasks().size() + " tasks in the list.";
    }
}