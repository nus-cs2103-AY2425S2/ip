package prophet.command;

import java.time.LocalDateTime;

import prophet.gui.Ui;
import prophet.storage.Storage;
import prophet.task.DeadlineTask;

/**
 * The AddDeadlineCommand class represents the command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private final String taskDescription;
    private final LocalDateTime deadline;

    /**
     * Initialises a newly created AddDeadlineCommand object.
     * @param type the type of command
     * @param taskDescription the description of the task
     * @param deadline the deadline of the task
     */
    public AddDeadlineCommand(CommandType type, String taskDescription, LocalDateTime deadline) {
        super(type);
        this.taskDescription = taskDescription;
        this.deadline = deadline;
    }

    /**
     * Adds a deadline task to the list of tasks.
     * @param ui the user interface of the chatbot
     * @param storage the storage of tasks
     * @return the message to be printed
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        DeadlineTask newTask = new DeadlineTask(this.taskDescription, this.deadline);
        String added = storage.addToList(newTask);
        assert added.equals("Task added to list: " + newTask) : "Task should always be added successfully";
        return ui.print(added + "\n");
    }
}
