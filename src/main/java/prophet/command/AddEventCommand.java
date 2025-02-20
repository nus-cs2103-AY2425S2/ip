package prophet.command;

import java.time.LocalDateTime;

import prophet.gui.Ui;
import prophet.storage.Storage;
import prophet.task.EventTask;

/**
 * The AddEventCommand class represents the command to add an event task.
 */
public class AddEventCommand extends Command {
    private final String taskDescription;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Initialises a newly created AddEventCommand object.
     * @param type the type of command
     * @param taskDescription the description of the task
     * @param from the start date of the event
     * @param to the end date of the event
     */
    public AddEventCommand(CommandType type, String taskDescription, LocalDateTime from, LocalDateTime to) {
        super(type);
        this.taskDescription = taskDescription;
        this.from = from;
        this.to = to;
    }

    /**
     * Adds an event task to the list of tasks.
     * Prints out the confirmation message or the error message.
     * @param ui the user interface of the chatbot
     * @param storage the storage of tasks accumulated as the chatbot runs
     * @return the message to be printed
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        EventTask newTask = new EventTask(this.taskDescription, this.from, this.to);
        String added = storage.addToList(newTask);
        assert added.equals("Task added to list: " + newTask) : "Task should always be added successfully";
        return ui.print(added + "\n");
    }
}
