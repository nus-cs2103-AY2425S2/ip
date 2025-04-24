package jimmy.commands;

import jimmy.JimmyException;
import jimmy.Storage;
import jimmy.Ui;
import jimmy.tasks.Event;
import jimmy.tasks.Task;
import jimmy.tasks.TaskList;

/**
 * The {@code AddEventCommand} class represents a command to add a new event task
 * to the task list. It parses the input to extract the event description, start time,
 * and end time, and handles validation to ensure proper formatting.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs an {@code AddEventCommand} by parsing the user's input.
     *
     * @param input the input string containing the event description, start time, and end time.
     *              The input format should be: {@code description /from [start time] /to [end time]}.
     * @throws JimmyException if the input format is invalid or missing required parts.
     */
    public AddEventCommand(String arguments) throws JimmyException {
        super();
        String[] parts = arguments.split(" /from | /to ");
        if (parts.length < 3) {
            throw new JimmyException("The event command must be in 'event"
                + " {NAME} /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm'.");
        }
        this.description = parts[0].trim();
        this.from = parts[1].trim();
        this.to = parts[2].trim();
    }

    /**
     * Executes the add-event command by creating a new event task, adding it to the task list,
     * saving the updated task list to storage, and notifying the user.
     *
     * @param tasks   the {@code TaskList} containing all current tasks.
     * @param ui      the {@code Ui} instance for displaying messages to the user.
     * @param storage the {@code Storage} instance responsible for saving tasks.
     * @throws JimmyException if an error occurs while creating or saving the event task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JimmyException {
        Task event = new Event(description, from, to);
        tasks.addTask(event);
        storage.save(tasks.getAllTasks());
        ui.showMessage("Got it. I've added this task:\n  " + event
                + "\nNow you have " + tasks.size() + " tasks in the list.");
        return "Got it. I've added this task:\n  " + event
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Indicates whether this command should terminate the program.
     *
     * @return {@code false} as the add-event command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
